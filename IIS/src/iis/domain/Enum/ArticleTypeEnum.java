package iis.domain.Enum;

public enum ArticleTypeEnum {
	NEWS("Новости"),
	STOCK("Акции");
	
	private final String label;

	private ArticleTypeEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
