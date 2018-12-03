package com.showtimedev.server_side.nav;

import javax.swing.*;
import java.awt.*;

public class NodeWarehouseVisualizer{
	
	public static void main(String[] args){
		NodeWarehouseVisualizer vis = new NodeWarehouseVisualizer();
		vis.run();
	}
	
	public void run(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				
				JFrame frame = new JFrame();
				frame.setSize(new Dimension(600, 500));
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				JPanel drawPanel = new JPanel(null);
				drawPanel.setSize(new Dimension(600, 500));
				drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				NodeWarehouseRenderer renderer = new NodeWarehouseRenderer(RawNodeWarehouseTest.testWarehouse());
				
				renderer.setBounds(0, 0, 600, 500);
				renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				
				renderer.repaint();
				drawPanel.repaint();
				
				drawPanel.add(renderer);
				
				frame.getContentPane().add(drawPanel);
				
			}
		});
		
	}
	
	
}
