package cn.edu.ruc.iir.wx.domain.wx;

/**
 * 
 * @Package entity
 * @ClassName: AccessToken
 * @Description: 接口访问凭证
 * @author Tao
 * @version V1.0
 * @date 2015年12月4日 上午9:14:32
 */
public class AccessToken {
	// 获取到的凭证
	private String accessToken;

	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getAccessToken() {

		return accessToken;
	}

	public void setAccessToken(String accessToken) {

		this.accessToken = accessToken;
	}

	public int getExpiresIn() {

		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {

		this.expiresIn = expiresIn;
	}

}
