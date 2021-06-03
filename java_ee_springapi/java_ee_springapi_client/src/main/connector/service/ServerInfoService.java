/*
 * This file is generated by PL/SQL Enterprise Workbench Connector Builder.
 * PL/SQL Enterprise Workbench Copyright (c) Jan Richter, Germany, Hamburg, 2015-2021. All rights reserved.
 *
 * THIS FILE IS NOT INTENDED TO BE MODIFIED - IT WILL BE OVERWRITTEN ON EVERY RUN OF THE CONNECTOR BUILDER
 */
package service;

import transferobject.ServerInfoTO;

/**
 * Service interface declaration for to call PL/SQL procedure <em>SERVER_INFO</em>.<p>
 */
public interface ServerInfoService {
  /**
   * Service implementation calls PL/SQL procedure <em>SERVER_INFO</em>.<p>
   *
   * Database server information.<p>
   *
   * @param iDiff Offset of date and timestamp value.
   * @return Transferobject of out parameters : oDate, oTimestamp, oInstance, oDbVersion, oDbRelease
   */
  ServerInfoTO call(Integer iDiff) throws Exception;
}
