
package org.meruvian.yama.core.commons;

import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.meruvian.yama.core.DefaultPersistence;


@Entity
@Table(name = "yama2showcase_file_info")
public class FileInfo extends DefaultPersistence {
	private String originalName;
	private String contentType;
	private String path;
	private long size = 0;
	private InputStream dataBlob;
	
	@Column(name = "content_type")
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Transient
	public InputStream getDataBlob() {
		return dataBlob;
	}
	
	public void setDataBlob(InputStream dataBlob) {
		this.dataBlob = dataBlob;
	}
	
	@Column(name = "original_name", nullable = false)
	public String getOriginalName() {
		return originalName;
	}
	
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	@Lob
	@Column(nullable = false)
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(nullable = false)
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
}
