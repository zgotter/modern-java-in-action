package modern_java_in_action.ch08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

class Transaction {
    String referenceCode;

    public Transaction(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getReferenceCode() {
        return this.referenceCode;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "referenceCode='" + referenceCode + '\'' +
                '}';
    }
}

public class Ch08_02_list_and_set_processing {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("a12"));
        transactions.add(new Transaction("b13"));
        transactions.add(new Transaction("c14"));
        transactions.add(new Transaction("123"));

//        for (Transaction transaction : transactions) {
//            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
//                transactions.remove(transaction);
//            }
//        }
//        System.out.println(transactions);

//        for (Iterator<Transaction> iterator = transactions.iterator();
//             iterator.hasNext();) {
//            Transaction transaction = iterator.next();
//            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
//                transactions.remove(transaction);
//            }
//        }
//        System.out.println(transactions);

//        for (Iterator<Transaction> iterator = transactions.iterator();
//             iterator.hasNext();) {
//            Transaction transaction = iterator.next();
//            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
//                iterator.remove();
//            }
//        }
//        System.out.println(transactions);

        transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
        System.out.println(transactions);

        List<String> referenceCodes = new ArrayList<>();
        referenceCodes.add("a12");
        referenceCodes.add("C14");
        referenceCodes.add("b13");

        referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println(referenceCodes);

        List<String> referenceCodes2 = new ArrayList<>();
        referenceCodes2.add("a12");
        referenceCodes2.add("C14");
        referenceCodes2.add("b13");

        for (ListIterator<String> iterator = referenceCodes2.listIterator();
             iterator.hasNext();) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }
        System.out.println(referenceCodes2);

        List<String> referenceCodes3 = new ArrayList<>();
        referenceCodes3.add("a12");
        referenceCodes3.add("C14");
        referenceCodes3.add("b13");

        referenceCodes3.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println(referenceCodes3);
    }
}
