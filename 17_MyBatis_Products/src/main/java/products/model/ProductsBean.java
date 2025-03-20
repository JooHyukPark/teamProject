package products.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ProductsBean {
	private int num;
	@Length(min = 3, max = 10, message="상품 이름은 최소 3자리 최대 10자리입니다.")
	private String name;
	private String company;
	@NotEmpty(message="화일 선택 안 함")
	private String image;
	private String stock;
	@Min(value = 3000, message = "가격은 최소 3000원 이상입니다.")
	private String price;
	private String category;
	@Length(min = 5, max = 10, message="상품 설명은 최소 5자리 최대 10자리입니다.")
	private String contents;
	private String point;
	private String inputdate;
	
	private MultipartFile upload;
	private String upload2;
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		System.out.println("setUpload upload: " + upload);
		this.upload = upload;
		System.out.println("upload.getName(): "+upload.getName());
		System.out.println("upload.getOriginalFilename(): "+upload.getOriginalFilename());
		this.image = upload.getOriginalFilename();
	}

	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	
	
}
