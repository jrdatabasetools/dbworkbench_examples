/*
 * This file is generated by PL/SQL Enterprise Workbench Connector Builder.
 * PL/SQL Enterprise Workbench Copyright (c) Jan Richter, Germany, Hamburg, 2015-2022. All rights reserved.
 *
 * THIS FILE IS NOT INTENDED TO BE MODIFIED - IT WILL BE OVERWRITTEN ON EVERY RUN OF THE CONNECTOR BUILDER
 */
package transferobject;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Transfer object returned from PL/SQL procedure <em>SERVER_INFO</em>.<p>
 */
final public class ServerInfoTO implements Serializable {
  private static final long serialVersionUID = 1L;

  /** System date plus iDiff offset. */ 
  public Date oDate;

  /** System timestamp plus iDiff offset. */ 
  public Timestamp oTimestamp;

  /** Instance information. */ 
  public String oInstance;

  /** Oracle version. */ 
  public int oDbVersion;

  /** Oracle release. */ 
  public int oDbRelease;
}
