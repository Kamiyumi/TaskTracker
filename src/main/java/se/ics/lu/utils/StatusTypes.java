package se.ics.lu.utils;

public enum StatusTypes {
    FAILED {
        public String toString() {
            return "FAILED";
        }
    },
    PROGRESS {
        public String toString() {
            return "IN PROGRESS";
        }
    },
    COMPLETED {
        public String toString() {
            return "COMPLETED";
        }
    }
}
