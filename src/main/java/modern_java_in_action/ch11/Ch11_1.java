package modern_java_in_action.ch11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Ch11_1 {
    public static void main(String[] args) {

    }
//    public class Person {
//        private Car car;
//        public Car getCar() {
//            return car;
//        }
//    }
//
//    public class Car {
//        private Insurance insurance;
//        public Insurance getInsurance() {
//            return insurance;
//        }
//    }
//
//    public class Insurance {
//        private String name;
//
//        public String getName() {
//            return name;
//        }
//    }

    public class Person {
        private Optional<Car> car;
        public Optional<Car> getCar() {
            return car;
        }
    }

    public class Car {
        private Optional<Insurance> insurance;
        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    public class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }

//    public String getCarInsuranceName(Person person) {
//        return person.getCar().getInsurance().getName();
//    }
//
//    public String getCarInsuranceName(Person person) {
//        if (person != null) {
//            Car car = person.getCar();
//            if (car != null) {
//                Insurance insurance = car.getInsurance();
//                if (insurance != null) {
//                    return insurance.getName();
//                }
//            }
//        }
//        return "Unknown";
//    }

//    public String getCarInsuranceName(Person person) {
//        if (person == null) {
//            return "Unknown";
//        }
//        Car car = person.getCar();
//        if (car == null) {
//            return "Unknown";
//        }
//        Insurance insurance = car.getInsurance();
//        if (insurance == null) {
//            return "Unknown";
//        }
//        return insurance.getName();
//    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar) // 사람 목록을 각 사람이 보유한 자동차의 Optional<Car> 스트림으로 변환
                .map(optCar -> optCar.flatMap(Car::getInsurance)) // FlatMap 연산을 이용해 Optional<Car> 을 Optional<Insurance> 로 변환
                .map(optIns -> optIns.map(Insurance::getName)) // Optional<Insurance> 를 해당 이름의 Optional<String> 으로 매핑
                .flatMap(Optional::stream) // Stream<Optional<String>>을 현재 이름을 포함하는 Stream<String> 으로 변환
                .collect(Collectors.toSet());
    }

}



