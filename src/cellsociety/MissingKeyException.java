package cellsociety;

/**
 * @author Ryan Feinberg
 * <p>
 * Purpose - The MissingKeyException.java class is responsible for throwing an exception
 * with a given message when an key is missing from the properties file
 * <p>
 * Assumptions - N/A
 * <p>
 * Dependencies - N/A
 */
public class MissingKeyException extends Exception {

  /**
   * Constructor, setup Exception with given message
   * @param msg
   */
  public MissingKeyException(String msg) {
    super(msg);
  }

}
