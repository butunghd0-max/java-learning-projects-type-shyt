public class Product {
    private final int id;
    private final boolean defective;

    public Product(int id, boolean defective) {
        this.id = id;
        this.defective = defective;
    }

    public int getId() {
        return id;
    }

    public boolean isDefective() {
        return defective;
    }
}
