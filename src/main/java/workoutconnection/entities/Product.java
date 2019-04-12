package workoutconnection.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="barcode")
	private String barcode;

	@Column(name="proteins")
	private float proteins;

	@Column(name="carbs")
	private float carbs;

	@Column(name="fats")
	private float fats;

	@Column(name="kcal")
	private float kcal;

	@Column(name="volume")
	private float volume;

	@Column(name="xyz")
	@ManyToMany(cascade = {CascadeType.PERSIST,
							CascadeType.MERGE},
				fetch = FetchType.EAGER)
	@JoinTable(name="meals_list",
				joinColumns = @JoinColumn(name = "meal_id"),
				inverseJoinColumns = @JoinColumn(name = "product_id"))
	@JsonIgnore
	private List<Meal> mealsList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public float getProteins() {
		return proteins;
	}
	public void setProteins(float proteins) {
		this.proteins = proteins;
	}
	public float getCarbs() {
		return carbs;
	}
	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}
	public float getFats() {
		return fats;
	}
	public void setFats(float fats) {
		this.fats = fats;
	}
	public float getKcal() {
		return kcal;
	}
	public void setKcal(float kcal) {
		this.kcal = kcal;
	}
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getName().hashCode();
	}
	@Override
	public boolean equals(Object obj) {

		Product xd = (Product)obj;

		return this.getName().compareTo(xd.getName()) == 0 ? true : false;

	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", barcode=" + barcode + ", proteins=" + proteins + ", carbs="
				+ carbs + ", fats=" + fats + ", kcal=" + kcal + ", volume=" + volume + ", association=]\n";
	}

	public List<Meal> getMealsList() {
		return mealsList;
	}

	public void setMealsList(List<Meal> mealsList) {
		this.mealsList = mealsList;
	}
	public static ProductBuilder builder(){
	    return new ProductBuilder();
    }
	public static final class ProductBuilder{

        private int id;
        private String name;
        private String barcode;
        private float proteins;
        private float carbs;
        private float fats;
        private float kcal;
        private float volume;

        public ProductBuilder setId(int id){
            this.id = id;
            return  this;
        }
        public ProductBuilder setName(String name){
            this.name = name;
            return this;
        }
        public ProductBuilder setBarcode(String barcode){
            this.barcode = barcode;
            return this;
        }
        public ProductBuilder setProteins(float proteins){
            this.proteins = proteins;
            return this;
        }
        public ProductBuilder setCarbs(float carbs){
            this.carbs = carbs;
            return this;
        }
        public ProductBuilder setFats(float fats){
            this.fats = fats;
            return this;
        }
        public ProductBuilder setKcal(float kcal){
            this.kcal = kcal;
            return this;
        }
        public ProductBuilder setVolume(String name){
            this.volume = volume;
            return this;
        }
        public Product build(){
            Product product = new Product();
            product.setId(this.id);
            product.setName(this.name);
            product.setBarcode(this.barcode);
            product.setCarbs(this.carbs);
            product.setFats(this.fats);
            product.setKcal(this.kcal);
            product.setProteins(this.proteins);
            product.setVolume(this.volume);

            return product;
        }
    }
}
