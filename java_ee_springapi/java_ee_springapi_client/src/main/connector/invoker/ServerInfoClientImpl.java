/*
* This file is generated by PL/SQL Enterprise Workbench Connector Builder.
 * PL/SQL Enterprise Workbench Copyright (c) Jan Richter, Germany, Hamburg, 2015-2021. All rights reserved.
 *
 * THIS FILE IS NOT INTENDED TO BE MODIFIED - IT WILL BE OVERWRITTEN ON EVERY RUN OF THE CONNECTOR BUILDER
 */
package invoker;

import javax.naming.Context;

import service.ServerInfoService;
import transferobject.ServerInfoTO;

public class ServerInfoClientImpl implements ServerInfoService {
  final private Context context;
  final private String jndiModulePrefix;

  public ServerInfoClientImpl(Context context, String jndiModulePrefix) {
    this.context = context;
    this.jndiModulePrefix = jndiModulePrefix;
  }

  public ServerInfoTO call(Integer iDiff) throws Exception {
    try {
      ServerInfoService service = (ServerInfoService) context.lookup(jndiModulePrefix + "/ServerInfoBean!service.ServerInfoService");
      return service.call(iDiff);
    }
    catch (Exception e) {
      if (e.getCause() != null && e.getCause() instanceof Exception) {
        throw (Exception) e.getCause();
      }
      else {
        throw e;
      }
    }
  }
}