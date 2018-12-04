package com.showtimedev.server_side.nav.raw_nav;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.showtimedev.server_side.nav.algorithms.AStarAlgorithm;
import com.showtimedev.server_side.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RawPathLoader{
	
	public RawPathLoader(){
		var loader = new CacheLoader<Pair<RawNode, RawNode>, List<RawNode>>(){
			@Override
			public List<RawNode> load(Pair<RawNode, RawNode> key){
				var algorithm = AStarAlgorithm.<RawNode>builder().build();
				var path = algorithm.findPath(new ArrayList<>(), key.a, key.b);
				return Objects.requireNonNull(path);
			}
		};
		
		cache = CacheBuilder.newBuilder()
				.expireAfterAccess(15, TimeUnit.MINUTES)
				.maximumSize(400)
				.build(loader);
	}
	
	private final LoadingCache<Pair<RawNode, RawNode>, List<RawNode>> cache;
	
	public List<RawNode> load(Pair<RawNode, RawNode> key){
		return cache.getUnchecked(key);
	}
	
	public List<RawNode> load(RawNode src, RawNode dest){
		return load(new Pair<>(src, dest));
	}
}
