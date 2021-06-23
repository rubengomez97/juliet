/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE319_Cleartext_Tx_Sensitive_Info__URLConnection_kerberosKey_52c.java
Label Definition File: CWE319_Cleartext_Tx_Sensitive_Info.label.xml
Template File: sources-sinks-52c.tmpl.java
*/
/*
 * @description
 * CWE: 319 Cleartext Transmission of Sensitive Information
 * BadSource: URLConnection Read password from a web server with URLConnection
 * GoodSource: Set password to a hardcoded value (one that was not sent over the network)
 * Sinks: kerberosKey
 *    GoodSink: Decrypt password before using in KerberosKey()
 *    BadSink : Use password directly in KerberosKey()
 * Flow Variant: 52 Data flow: data passed as an argument from one method to another to another in three different classes in the same package
 *
 * */

package testcases.CWE319_Cleartext_Tx_Sensitive_Info;

import testcasesupport.*;

import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosKey;

import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;

public class CWE319_Cleartext_Tx_Sensitive_Info__URLConnection_kerberosKey_52c
{
    public void badSink(String password ) throws Throwable
    {

        if (password != null)
        {
            KerberosPrincipal principal = new KerberosPrincipal("test");
            /* POTENTIAL FLAW: Use password directly in KerberosKey() */
            KerberosKey key = new KerberosKey(principal, password.toCharArray(), null);
            IO.writeLine(key.toString());
        }

    }

    /* goodG2B() - use goodsource and badsink */
    public void goodG2BSink(String password ) throws Throwable
    {

        if (password != null)
        {
            KerberosPrincipal principal = new KerberosPrincipal("test");
            /* POTENTIAL FLAW: Use password directly in KerberosKey() */
            KerberosKey key = new KerberosKey(principal, password.toCharArray(), null);
            IO.writeLine(key.toString());
        }

    }

    /* goodB2G() - use badsource and goodsink */
    public void goodB2GSink(String password ) throws Throwable
    {

        if (password != null)
        {
            KerberosPrincipal principal = new KerberosPrincipal("test");
            /* FIX: Decrypt password before using in KerberosKey() */
            {
                Cipher aesCipher = Cipher.getInstance("AES");
                /* INCIDENTAL: CWE-321: Use of Hard-coded Cryptographic Key */
                SecretKeySpec secretKeySpec = new SecretKeySpec("ABCDEFGHABCDEFGH".getBytes("UTF-8"), "AES");
                aesCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                password = new String(aesCipher.doFinal(password.getBytes("UTF-8")), "UTF-8");
            }
            KerberosKey key = new KerberosKey(principal, password.toCharArray(), null);
            IO.writeLine(key.toString());
        }

    }
}
