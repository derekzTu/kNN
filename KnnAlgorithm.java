package knnalgorithm;
import java.util.*;
import java.io.*;

public class KnnAlgorithm {
//////////instance variables//////////
	
	private LinkedHashMap<Point, Character> pointToCharacter;
	private static LinkedHashMap<Character,Double> weightedVariable;
	private LinkedHashMap<Character, List<Double>> characterDistances;
	private LinkedList<Double> characterDistance;
	private ArrayList<Character> presentCharacters;
	private Character classification;
	
//////////constructors//////////
	
	public KnnAlgorithm(){
		this.pointToCharacter = new LinkedHashMap<Point,Character>();
		this.weightedVariable=new LinkedHashMap<Character,Double>();
		this.characterDistances= new LinkedHashMap<Character,List<Double>>();
		this.characterDistance = new LinkedList<Double>();
		this.presentCharacters=  new ArrayList<Character>();
		this.classification= Character.MIN_VALUE;
	}	
	
//////////methods//////////
	
	public boolean addToDataSet(Point point, Character element){
		//if data point already exists, then do not add
		if(this.pointToCharacter.containsKey(point)){
			System.err.println("Point already in use");
			return false;
		}
		
		this.pointToCharacter.put(point, element);
		this.presentCharacters.add(element);
		return true;
	}
	
	public boolean knnAlogrithm(int kNN, Character element, Point elementPoint){
		
		//Case 0: If kNN is a multiple of the amount of elements
		if(this.presentCharacters.size()%kNN==0){
			System.err.println("kNN cannot be a "
					           + "multiple of amount of known elements");	
			return false;
		}
		
		//Case 1: kNN =1 
		else if(kNN==1){
			//for now voronoiSpace has not been started so
			this.classification = voronoiSpace(elementPoint, element);
			//this.classification will be set to an arbitary value
			this.classification = 'a';
		return true;
		}
		//Case 2; kNN > 1
		else{
			LinkedList pointSet = new LinkedList<Point>(this.pointToCharacter.keySet());
			
		//finding distance between this point and all points
		for(int i =0; i<pointSet.size();i++){
			double newDistance = distanceBetween((Point)pointSet.get(i),elementPoint);
			
			this.characterDistance.add(newDistance);
			
			this.characterDistances.put
			                        (pointToCharacter.get(pointSet.get(i)), 
			                        this.characterDistance);
		}
		
		//Sorting the data points 
		for(Character key:characterDistances.keySet()){
			Collections.sort(characterDistances.get(key));
			characterDistances.put(key,characterDistances.get(key));
			
		}
		//finding the weighted value of each kNN amount of elements
		for(Character key: characterDistances.keySet()){
			List<Double> topN = new LinkedList<>(characterDistances.get(key));
			int removeIndex = 0;
			//removing variables until only top N variables remain
			while(topN.size()!=kNN){
				topN.remove(removeIndex);
				removeIndex++;
			}
			//calculating weightedValue for top N of each character
			double weightedValue = 0.0;
			for (int i =0; i<topN.size();i++){
				weightedValue = 1/topN.get(i);
			}
			
			this.weightedVariable.put(key, weightedValue);
				
		}
		
		ArrayList<Character> orderCharacter =  new ArrayList<Character>(this.weightedVariable.keySet());
		Collections.sort(orderCharacter,new weightingCompare());
		
		//point gets set to element with highest weighting value
		this.classification = orderCharacter.get(orderCharacter.size()-1);
		
		//point gets added to set of known values
		addToDataSet(elementPoint, this.classification);
		
		return true;
		}
	}
	public static double distanceBetween(Point pointOne, Point pointTwo){
		
		double distance = 0.0;
		
		double x = pointOne.getX()-pointTwo.getX();
		x = Math.pow(x,2);
		
		double y = pointOne.getY() - pointTwo.getY();
		y = Math.pow(y, 2);
		//Finding distance using Pythagorean Theorem
		distance = Math.sqrt(x+y);
		return distance;
		
		}
	
	public Character voronoiSpace(Point point, Character newClassification){
		
		
		return '0';
	
	}
	
	//
	private static class weightingCompare implements Comparator<Character>{
		public int compare(Character a, Character b){
		    return weightedVariable.get(a).compareTo(weightedVariable.get(b));
			
		}
	}

}
