package hibernate_package;

import javax.persistence.*;

@Entity
@Table(name = "stocklist")
public class stocklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "price")
	private Float price;
	
	@Column(name = "avgYield")
	private Float avgYield;
	
	@Column(name = "divYield")
	private Float divYield;
	
	@Column(name = "shares")
	private int shares;

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getsymbol() {
		return symbol;
	}

	public void setsymbol(String symbol) {
		this.symbol = symbol;
	}

	public Float getprice() {
		return price;
	}

	public void setprice(Float price) {
		this.price = price;
	}

	public Float getavgYield() {
		return avgYield;
	}

	public void setavgYield(Float avgYield) {
		this.avgYield = avgYield;
	}

	public Float getdivYield() {
		return divYield;
	}

	public void setdivYield(Float divYield) {
		this.divYield = divYield;
	}
	
	public int getshares() {
		return shares;
	}

	public void setshares(int shares) {
		this.shares = shares;
	}
}
