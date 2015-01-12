package xingu.inspector;

import java.util.Map;

import xingu.utils.MapUtils;

public class WithMap
{
	Map<String, SimpleObject> map;
	
	public WithMap()
	{}
	
	public WithMap(Map<String, SimpleObject> map)
	{
		this.map = map;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof WithMap))
		{
			return false;
		}
		
		WithMap other = (WithMap) obj;
		return MapUtils.equals(map, other.map);
	}
}
