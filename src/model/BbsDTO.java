package model;
/*
DTO 클래스를 만들때는 테이블정의서를 참조한다.
기본적으로 테이블과 동일한 형태로 멤버변수를 정의하면 된다.
멤버변수의 타입은 특별한 경우를 제외하고는 대부분 String으로
정의한다. 꼭 필요한 경우에만 int, double로 정의한다.
*/
public class BbsDTO {
	private String num;//일련변호
	private String title;//제목
	private String content;//내용
	private String id;//작성자아이디(member테이블 참조)
	private java.sql.Date postDate;//작성일
	private String visitcount;//조회수
	
	/*
	회원테이블과 join하여 이름을 가져오기 위해 DTO클래스에	
	name컬럼을 추가한다.
	*/
	
	private String name;
	
	
	//생성자는 기술하지 않는다.
	//getter/setter만 기술한다.
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the postdate
	 */
	public java.sql.Date getPostDate() {
		return postDate;
	}
	/**
	 * @param postdate the postdate to set
	 */
	public void setPostDate(java.sql.Date postdate) {
		this.postDate = postdate;
	}
	/**
	 * @return the visitcount
	 */
	public String getVisitcount() {
		return visitcount;
	}
	/**
	 * @param visitcount the visitcount to set
	 */
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	
	
	
	
	
	
}
