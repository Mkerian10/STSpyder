package com.showtimedev.nav;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class NodeWarehouseRenderer extends JPanel{
	
	
	public NodeWarehouseRenderer(NodeWarehouse nw){
		this.nw = nw;
		
		nodes = nodes(nw);
		minX = nodes.stream().mapToInt(value -> value.x).min().getAsInt();
		maxX = nodes.stream().mapToInt(value -> value.x).max().getAsInt();
		minY = nodes.stream().mapToInt(value -> value.y).min().getAsInt();
		maxY = nodes.stream().mapToInt(value -> value.y).max().getAsInt();
		
	}
	
	private final int minX;
	
	private final int maxX;
	
	private final int minY;
	
	private final int maxY;
	
	private final NodeWarehouse nw;
	
	private final Collection<RawNode> nodes;
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setClip(null);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100, 100);
		
		for(int i = minX; i <= maxX; i++){
			for(int j = minY; j <= maxY; j++){
				RawNode rn = nw.retrieve(i, j, 0);
				Point start = new Point(row(i) * blockWidth(), column(j) * blockHeight());
				
				if(rn == null){
					g.setColor(Color.DARK_GRAY);
				}else{
					if((rn.navFlags & RawNode.SE) == 0){
						g.setColor(Color.GRAY);
					}else{
						g.setColor(Color.GREEN);
					}
				}
				g.fillRect(start.x, start.y, blockWidth(), blockHeight());
				g.setColor(Color.WHITE);
				g.drawRect(start.x, start.y, blockWidth(), blockHeight());
			}
		}
		
	}
	
	private int rows(){
		Set<Integer> set = new HashSet<>();
		nodes.forEach(rawNode -> set.add((int) rawNode.x));
		return set.size();
	}
	
	private int columns(){
		Set<Integer> set = new HashSet<>();
		nodes.forEach(rawNode -> set.add((int) rawNode.y));
		return set.size();
	}
	
	private int row(int x){
		Set<Integer> set = new HashSet<>();
		nodes.forEach(rawNode -> set.add((int) rawNode.x));
		return set.stream().sorted(Comparator.comparingInt(v -> v)).collect(Collectors.toList()).indexOf(x);
	}
	
	private int column(int y){
		Set<Integer> set = new HashSet<>();
		nodes.forEach(rawNode -> set.add((int) rawNode.y));
		return set.stream().sorted(Comparator.comparingInt(v -> v)).collect(Collectors.toList()).indexOf(y);
	}
	
	private int blockWidth(){
		return getWidth() / (maxX - minX + 1);
	}
	
	private int blockHeight(){
		return 43;
	}
	
	private final Collection<RawNode> nodes(NodeWarehouse nw){
		try{
			Field f = NodeWarehouse.class.getDeclaredField("worldGraph");
			f.setAccessible(true);
			
			Map<Integer, RawNode> map = (Map<Integer, RawNode>) f.get(nw);
			return map.values();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
