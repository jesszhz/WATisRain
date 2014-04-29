package com.lucky.watisrain.backend.data;

import java.util.ArrayList;
import java.util.List;

import com.lucky.watisrain.backend.Util;

/**
 * A Building is a wrapper for a list of Location objects, each of which
 * represents a floor of a building.
 */
public class Building {

	// Floors of this building
	private List<Location> floors;
	
	// Stairs to get around within this building
	private List<Path> stairs;
	
	// Short name, like "DC"
	private String name;
	
	// Location of this building
	private Waypoint position;
	
	// How many floors does this building have?
	private int num_floors;
	
	// Which of the floors is the main floor? By default, 1
	private int main_floor;
	
	
	/**
	 * Constructor. Assumes all floors have this same position, main_floor <= num_floors
	 */
	public Building(String name, Waypoint position, int num_floors, int main_floor){
		
		this.name = name;
		this.position = position;
		this.main_floor = main_floor;
		this.num_floors = num_floors;
		
		// Populate list of floors
		floors = new ArrayList<>();
		for(int i=1; i<=num_floors; i++){
			String floor_id = Util.makeBuildingAndFloor(name, i);
			Location this_floor = new Location(floor_id,position,true);
			floors.add(this_floor);
		}
		
		// Populate list of stairs
		stairs = new ArrayList<>();
		for(int i=1; i<num_floors; i++){
			Location lower_floor = floors.get(i-1);
			Location upper_floor = floors.get(i);
			Path this_stair = new Path(lower_floor,upper_floor);
			this_stair.setPathType(Path.TYPE_STAIR);
			stairs.add(this_stair);
		}
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * Return the main floor
	 */
	public Location getMainFloor(){
		return floors.get(main_floor-1);
	}
	
	/**
	 * Numerical value of main floor
	 */
	public int getMainFloorNumber(){
		return main_floor;
	}
	
	public List<Location> getAllFloors(){
		return floors;
	}
	
	public List<Path> getAllStairs(){
		return stairs;
	}
	
	public Waypoint getPosition(){
		return position;
	}
	
	public int getNumberOfFloors(){
		return num_floors;
	}
	
	public String toString(){
		return floors.toString();
	}
	
}
