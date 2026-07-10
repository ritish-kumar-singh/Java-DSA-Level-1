package backtracking;

import java.util.Scanner;

public class L17 {
    static void main() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] maze=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print("maze["+i+"]["+j +"] : " );
                maze[i][j]=sc.nextInt();
            }
        }

        boolean[][] visited=new boolean[n][m];


        floodFill(maze, 0,0,"",visited);
    }


//    Flood Fill
    public static void floodFill(int[][] maze, int sr, int sc, String ans, boolean[][] visited){
        if(sr<0 || sc<0 || sr>=maze.length || sc>=maze[0].length || maze[sr][sc]==1 || visited[sr][sc]==true){
            return;
        }

        if(sr==maze.length-1 && sc==maze[0].length-1 ){  // reached destination
            System.out.println(ans);
            return;
        }

        // to avoid infinite recursion
        visited[sr][sc]=true;  // to avoid the spot already visited in the current path

        floodFill(maze, sr-1, sc, ans+"t", visited);  // top
        floodFill(maze, sr+1, sc, ans+"d", visited);  // down
        floodFill(maze, sr, sc-1, ans+"l", visited);  // left
        floodFill(maze, sr, sc+1, ans+"r", visited);  // right

        visited[sr][sc]=false;  // to mark the spot as not visited for next path
    }
}
