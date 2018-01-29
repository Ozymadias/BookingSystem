package bookingsystem;

class MyPair<T, T1> {
    private final T day;
    private final T1 i;

    MyPair(T day, T1 i) {
        this.day = day;
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPair<?, ?> myPair = (MyPair<?, ?>) o;

        if (day != null ? !day.equals(myPair.day) : myPair.day != null) return false;
        return i != null ? i.equals(myPair.i) : myPair.i == null;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (i != null ? i.hashCode() : 0);
        return result;
    }
}
