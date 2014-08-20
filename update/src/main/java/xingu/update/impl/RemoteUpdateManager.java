package xingu.update.impl;

import java.io.InputStream;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;

import xingu.http.client.HttpClient;
import xingu.http.client.HttpRequest;
import xingu.http.client.HttpResponse;
import xingu.update.BundleDescriptor;
import xingu.update.BundleDescriptors;
import xingu.update.UpdateManager;
import br.com.ibnetwork.xingu.container.Inject;

public class RemoteUpdateManager
	extends UpdateManagerSupport
	implements UpdateManager, Configurable, Initializable
{
	@Inject("update-manager")
	protected HttpClient	http;

	protected String		remote;

	@Override
	public void configure(Configuration conf)
		throws ConfigurationException
	{
		super.configure(conf);
		conf = conf.getChild("repo");
		this.remote = conf.getAttribute("remote");
		logger.info("Remote Repository: '{}'", remote);
	}

	@Override
	protected BundleDescriptors getRemoteDescriptors()
		throws Exception
	{
		String uri = remote + "/" + bundlesFile;
		logger.info("Loading remote descriptors from {}", uri);

		HttpRequest  req = http.get(uri);
		HttpResponse res = req.exec();
		InputStream  is  = res.getRawBody();
		return parse(is);
	}
	
	@Override
	protected InputStream toInputStream(BundleDescriptor desc)
	{
		String       file = desc.getFile();
		String       id   = desc.getId();
		String       uri  = this.remote + "/" + id + "/" + file;

		logger.info("Downloading '{}'", uri);
		HttpResponse res = http.get(uri).exec();
		logger.info("Download done '{}'", uri);
		
		return res.getRawBody();
	}
}
