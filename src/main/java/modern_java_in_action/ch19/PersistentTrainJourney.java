package modern_java_in_action.ch19;

public class PersistentTrainJourney {
    static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) {
            return b;
        }
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }

    static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    static class TrainJourney {
        public int price;
        public TrainJourney onward;

        public TrainJourney(int p, TrainJourney t) {
            this.price = p;
            this.onward = t;
        }
    }
}

