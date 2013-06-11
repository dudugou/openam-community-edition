/**
 * Copyright 2013 ForgeRock, Inc.
 *
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 */

/**
 * Fields of the Core Token Service. The LDAP backed data store can only store
 * named attributes, therefore each attribute to be stored must be mapped to
 * one of the designated LDAP attributes. The Field enumeration classes provide
 * this mapping. The CoreTokenField being the primary source for the LDAP
 * attribute names.
 */

package com.sun.identity.sm.ldap.api.fields;