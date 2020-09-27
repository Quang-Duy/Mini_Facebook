package mvc;

/**
 * Create a new model
 * @author Quang-Duy Tran
 *
 */

public abstract class Model extends Bean {
	private static final long serialVersionUID = 1L;

	private String fileName = null;
	private Boolean unsavedChanges = false;
	
	/**
	 * Get the file name
	 * @return return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Set the fileName to fileName
	 * @param fileName the file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * True if is unsaved; False otherwise
	 * @return true/false
	 */
	public Boolean getUnsavedChanges() {
		return unsavedChanges;
	}
	
	/**
	 * Set this unsavedChanges to unsavedChanges
	 * @param unsavedChanges the unsavedChanges
	 */
	public void setUnsavedChanges(Boolean unsavedChanges) {
		this.unsavedChanges = unsavedChanges;
	}

	/**
	 * Save the unsaved
	 */
	public void changed() {
		unsavedChanges = true;
		firePropertyChange(null, null, null);
	}
}