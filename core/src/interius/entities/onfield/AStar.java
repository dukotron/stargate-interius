package interius.entities.onfield;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {
    static class Cell {
        int hCost = 0;
        int fCost = 0;
        int x, y;
        Cell parent;
        
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int[] toIntPair(){
            int[] a = {this.x, this.y};
            return a;
        }
        
        @Override
        public String toString() {
            return "["+this.x+", "+this.y+"]";
        }
    }
    
    public static final int DIAGONAL_COST = 141;
    public static final int VH_COST = 100;
    
    static Cell [][] grid;
    static PriorityQueue<Cell> open;
    static boolean closed[][];
    static int startX, startY;
    static int endX, endY;
    
    public static void setBlocked(int x, int y){
        grid[x][y] = null;
    }
    
    public static void setStart(int x, int y) {
        startX = x;
        startY = y;
    }
    
    public static void setEnd(int x, int y) {
        endX = x;
        endY = y;
    }
    
    static void checkAndUpdateCost(Cell current, Cell neighbour, int cost){
        if(neighbour == null || closed[neighbour.x][neighbour.y])
            return;
        
        int neighbourFCost = neighbour.hCost + cost;
        boolean inOpen = open.contains(neighbour);
        
        if(!inOpen || neighbourFCost < neighbour.fCost) {
            neighbour.fCost = neighbourFCost;
            neighbour.parent = current;
            
            if(!inOpen)
                open.add(neighbour);
        }
    }
    
    public static void Algorithm(){ 
        
        open.add(grid[startX][startY]);
        Cell current;
        
        while(true){ 
            current = open.poll();
            if(current == null)
                break;
            
            closed[current.x][current.y] = true; 

            if(current.equals(grid[endX][endY]))
                return; 

            Cell neighbour;  
            if(current.x - 1 >= 0) {
                neighbour = grid[current.x - 1][current.y];
                checkAndUpdateCost(current, neighbour, current.fCost + VH_COST); 

                if(current.y - 1 >= 0){                      
                    neighbour = grid[current.x - 1][current.y - 1];
                    checkAndUpdateCost(current, neighbour, current.fCost + DIAGONAL_COST); 
                }

                if(current.y + 1 < grid[0].length){
                    neighbour = grid[current.x - 1][current.y + 1];
                    checkAndUpdateCost(current, neighbour, current.fCost + DIAGONAL_COST); 
                }
            } 

            if(current.y - 1 >= 0){
                neighbour = grid[current.x][current.y - 1];
                checkAndUpdateCost(current, neighbour, current.fCost + VH_COST); 
            }

            if(current.y + 1 < grid[0].length){
                neighbour = grid[current.x][current.y + 1];
                checkAndUpdateCost(current, neighbour, current.fCost + VH_COST); 
            }

            if(current.x + 1 < grid.length){
                neighbour = grid[current.x + 1][current.y];
                checkAndUpdateCost(current, neighbour, current.fCost + VH_COST); 

                if(current.y - 1 >= 0){
                    neighbour = grid[current.x + 1][current.y - 1];
                    checkAndUpdateCost(current, neighbour, current.fCost + DIAGONAL_COST); 
                }
                
                if(current.y + 1 < grid[0].length){
                    neighbour = grid[current.x + 1][current.y + 1];
                    checkAndUpdateCost(current, neighbour, current.fCost + DIAGONAL_COST); 
                }  
            }
        } 
    }
    
    public static Queue<int[]> ApplyAlgorithm(int sizeX, int sizeY, int startX, int startY,
            int endX, int endY, int[][] blocked) {
        
        Queue<int[]> result = new LinkedList<int[]>();
        grid = new Cell[sizeX][sizeY];
        closed = new boolean[sizeX][sizeY];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
             Cell c1 = (Cell)o1;
             Cell c2 = (Cell)o2;

             return c1.fCost<c2.fCost?-1:
                     c1.fCost>c2.fCost?1:0;
         });
        
        setStart(startX, startY);
        setEnd(endX, endY); 
        
        for(int x = 0; x < sizeX; x++) {
           for(int y = 0; y < sizeY; y++) {
               grid[x][y] = new Cell(x, y);
               grid[x][y].hCost = Math.abs(x - endX) + Math.abs(y - endY);
           }
        }
        grid[startX][startY].fCost = 0;
        
        for(int i = 0; i < blocked.length; i++) {
            setBlocked(blocked[i][0], blocked[i][1]);
        }
        
        /*System.out.println("Grid: ");
         for(int x = 0; x < sizeX; x++) {
             for(int y = 0; y < sizeY; y++) {
                if(x == startX && y == startY)
                    System.out.print("SO  ");
                else if(x==endX && y==endY)
                    System.out.print("DE  ");
                else if(grid[x][y] != null)
                    System.out.printf("%-3d ", 0);
                else 
                    System.out.print("BL  "); 
             }
             System.out.println();
         } 
         System.out.println();*/
        
        Algorithm();
        
        /*System.out.println("\nScores for cells: ");
        for(int x = 0; x < sizeX; x++) {
            for(int y = 0; y < sizeX; y++) {
                if(grid[x][y] != null)
                    System.out.printf("%-3d ", grid[x][y].fCost);
                else 
                    System.out.print("BL  ");
            }
            System.out.println();
        }
        System.out.println();*/
         
        if(closed[endX][endY]) {
             System.out.println("Path: ");
             Cell current = grid[endX][endY];
             System.out.print(current);
             result.add(current.toIntPair());
             while(current.parent != null){
                 System.out.print(" -> " + current.parent);
                 result.add(current.parent.toIntPair());
                 current = current.parent;
             } 
             System.out.println();
        } else 
            System.out.println("No possible path");
        
        return result;
    }
}