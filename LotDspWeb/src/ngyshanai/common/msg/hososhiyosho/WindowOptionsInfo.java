package ngyshanai.common.msg.hososhiyosho;

import nis.framework.dictionary.InputModel;

@InputModel
public class WindowOptionsInfo {
	
	private String fullscreen;
	private String toolbar;
	private String location;
	private String menubar;
	private String scrollbars;
	private String resizable;
	private int top;
	private int left;
	private int width;
	private int height;
	
	public String getFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(String fullscreen) {
		this.fullscreen = fullscreen;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMenubar() {
		return menubar;
	}

	public void setMenubar(String menubar) {
		this.menubar = menubar;
	}

	public String getScrollbars() {
		return scrollbars;
	}

	public void setScrollbars(String scrollbars) {
		this.scrollbars = scrollbars;
	}

	public String getResizable() {
		return resizable;
	}

	public void setResizable(String resizable) {
		this.resizable = resizable;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
