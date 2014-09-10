package br.com.ibnetwork.xingu.crypto.impl.rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.commons.io.IOUtils;

import br.com.ibnetwork.xingu.crypto.PubKey;
import br.com.ibnetwork.xingu.crypto.impl.CryptoSupport;
import br.com.ibnetwork.xingu.lang.NotImplementedYet;
import br.com.ibnetwork.xingu.utils.FSUtils;
import br.com.ibnetwork.xingu.utils.MD5Utils;

public class RSACrypto
    extends CryptoSupport
    implements Initializable
{
    private KeyFactory keyFactory;
    
    private String ALGORITHM = "RSA";
    
    @Override
    public void initialize() 
        throws Exception
    {
        keyFactory = KeyFactory.getInstance(ALGORITHM);
    }

    @Override
    public byte[] decrypt(InputStream is, Key key)
        throws Exception
    {
        return RSAUtils.decrypt(is, key);
    }

    @Override
    public byte[] encrypt(InputStream is, Key key)
        throws Exception
    {
        return RSAUtils.encrypt(is, key);
    }

    @Override
    public PrivateKey readPrivateKey(String fileName, long id, String passphrase)
        throws Exception
    {
        File file = FSUtils.loadAsFile(fileName);
        return readPrivateKey(new FileInputStream(file), id, passphrase);
    }

    @Override
    public PrivateKey readPrivateKey(InputStream is, long id, String passphrase)
        throws Exception
    {
        //RSA private keys are encoded with PKCS8 (DER)
        byte[] encodedKey = IOUtils.toByteArray(is);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(encodedKey);
        PrivateKey key = keyFactory.generatePrivate(spec);
        return key;
    }

    @Override
    public PubKey readPublicKey(String fileName) 
        throws Exception
    {
        File file = FSUtils.loadAsFile(fileName);
        return readPublicKey(new FileInputStream(file));
    }

    @Override
    public PubKey readPublicKey(InputStream is) 
        throws Exception
    {
        //RSA public keys are encoded with X509
        byte[] encodedKey = IOUtils.toByteArray(is);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedKey);
        PublicKey key = keyFactory.generatePublic(spec);
        return wrapp(key);
    }

    private PubKey wrapp(PublicKey key)
    {
        return new PubKeyWrapper(key);
    }
}

class PubKeyWrapper
    implements PubKey
{
    private PublicKey key;

    public PubKeyWrapper(PublicKey key)
    {
        this.key = key;
    }

    @Override
    public int bitStrength()
    {
        throw new NotImplementedYet();
    }

    @Override
    public boolean isEncryptionKey()
    {
        throw new NotImplementedYet();
    }

    @Override
    public long keyId()
    {
        return -1;
    }

    @Override
    public String getAlgorithm()
    {
        return key.getAlgorithm();
    }

    @Override
    public byte[] getEncoded()
    {
        return key.getEncoded();
    }

    @Override
    public String getFormat()
    {
        return key.getFormat();
    }

    @Override
    public Key wrappedKey()
    {
        return key;
    }
    
    @Override
    public String hash()
    {
        byte[] encoded = key.getEncoded();
        String hash = MD5Utils.md5Hash(encoded);
        return hash;
    }
}
