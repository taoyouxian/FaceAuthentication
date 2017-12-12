package cn.edu.ruc.iir.wx.domain.msg.resp;

/**
 * 
 * @Package wx.msg.resp
 * @ClassName: Article
 * @Description: 图文model
 * @author Tao
 * @date 2016年3月13日 上午11:46:49
 */
public class Article {

	// 图文消息标题
	private String Title;

	// 图文消息描述
	private String Description;

	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;

	// 点击图文消息跳转链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
