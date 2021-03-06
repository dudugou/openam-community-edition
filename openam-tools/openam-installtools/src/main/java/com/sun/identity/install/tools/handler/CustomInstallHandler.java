/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2006 Sun Microsystems Inc. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * https://opensso.dev.java.net/public/CDDLv1.0.html or
 * opensso/legal/CDDLv1.0.txt
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at opensso/legal/CDDLv1.0.txt.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 */

/*
 * Portions Copyrighted [2010] [ForgeRock AS]
 */
package com.sun.identity.install.tools.handler;

import com.sun.identity.install.tools.admin.IToolsOptionHandler;
import com.sun.identity.install.tools.configurator.InstallAbortException;
import com.sun.identity.install.tools.configurator.InstallConstants;
import com.sun.identity.install.tools.configurator.CustomInstallDriver;
import com.sun.identity.install.tools.configurator.InstallException;
import com.sun.identity.install.tools.configurator.InstallLogger;
import com.sun.identity.install.tools.util.Console;
import com.sun.identity.install.tools.util.Debug;
import com.sun.identity.install.tools.util.LocalizedMessage;
import java.util.List;

public class CustomInstallHandler extends ConfigHandlerBase implements
        IToolsOptionHandler, InstallConstants {
    public CustomInstallHandler() {
        super();
    }

    public boolean checkArguments(List arguments) {
        return verifyArguments(arguments);
    }

    public void handleRequest(List arguments) {

        try {
            // Create an InstallLogger
            InstallLogger installLog = new InstallLogger(STR_CUSTOM);
            CustomInstallDriver driver = new CustomInstallDriver();
            if (arguments.isEmpty()) {
                Debug.log("CustomInstallHandler: invoked with 0 arguments.");
                driver.install(installLog);
            } else if (arguments.size() == 2) {
                Debug.log("CustomInstallHandler: invoked with 2 arguments - "
                        + arguments);

                String type = (String) arguments.get(0);
                String responseFileName = (String) arguments.get(1);

                if (type.equals(STR_SAVE_RESPONSES)) {
                    driver.install(responseFileName,
                            INT_OPERATION_TYPE_SAVE_RESPONSE, installLog);
                } else if (type.equals(STR_USE_RESPONSES)) {
                    driver.install(responseFileName,
                            INT_OPERATION_TYPE_USE_RESPONSE, installLog);
                }
            }
        } catch (InstallAbortException ia) {
            Debug.log("CustomInstallHandler: User Requested Abort ", ia);
            Console.printlnRawText(ia.getMessage());
        } catch (InstallException ex) {
            Debug.log("CustomInstallHandler: Failed to process install request ", 
                    ex);
            printConsoleMessage(LOC_HR_ERR_INSTALL, new Object[] { ex
                    .getMessage() });
        }
    }

    public void displayHelp() {
        Console.println();
        Console.println(
	    LocalizedMessage.get(LOC_HR_MSG_CUSTOM_INSTALL_USAGE_DESC));
        Console.println();
        Console.println(
	    LocalizedMessage.get(LOC_HR_MSG_CUSTOM_INSTALL_OPTIONS_DESC));
        Console.println(LocalizedMessage.get(LOC_HR_MSG_USE_RESP_OPT_DESC));
        Console.println(LocalizedMessage.get(LOC_HR_MSG_SAVE_RESP_OPT_DESC));
        Console.println();
    }

    public static final String LOC_HR_ERR_INSTALL = "HR_ERR_INSTALL";

    public static final String LOC_HR_MSG_CUSTOM_INSTALL_USAGE_DESC = 
        "HR_MSG_CUSTOM_INSTALL_USAGE_DESC";

    public static final String LOC_HR_MSG_CUSTOM_INSTALL_OPTIONS_DESC = 
        "HR_MSG_CUSTOM_INSTALL_OPTIONS_DESC";

    public static final String LOC_HR_MSG_USE_RESP_OPT_DESC = 
        "HR_MSG_USE_RESP_OPT_DESC";

    public static final String LOC_HR_MSG_SAVE_RESP_OPT_DESC = 
        "HR_MSG_SAVE_RESP_OPT_DESC";
}
