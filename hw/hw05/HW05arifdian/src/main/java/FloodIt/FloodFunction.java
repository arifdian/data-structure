package FloodIt;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class FloodFunction
{
    private Driver driver;
    //here floodList is declared as a LinkedList
    //it is declared as public (a bad practice), but it is needed by the Driver class
    public List<Coord> floodList = new LinkedList<Coord>();
    
    //constructor  
    public FloodFunction(Driver newDriver)
    {
        driver = newDriver;
        //when the game starts, only the cell at the left/top corner is filled
        floodList.add(new Coord(0, 0));
    }

    public void flood(int colorToFlood)
    {
        int i = 0;
        while (i < floodList.size())
        {
            Coord c = floodList.get(i);
            Coord up = up(c);
            Coord left = left(c);
            Coord right = right(c);
            Coord down = down(c);

            if (inbound(down)  && floodList.contains(down) == false && (colorToFlood == driver.getColor(down)))
            {
                floodList.add(down);
            }
            else if (inbound(left)  && floodList.contains(left) == false && (colorToFlood == driver.getColor(left)))
            {
                floodList.add(left);
            }
            else if (inbound(right)  && floodList.contains(right) == false && (colorToFlood == driver.getColor(right)))
            {
                floodList.add(right);
            }
            else if (inbound(up)  && floodList.contains(up) == false && (colorToFlood == driver.getColor(up)))
            {
                floodList.add(up);
            }
            i++;
        }
    }




    public int downblueColor (List<Coord> alist ){

        int  numblue=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 0) {
                    numblue = numblue + 1;
                }
            }
            i++;
        }
        return numblue;
    }

    public int leftblueColor (List<Coord> alist ){
        int  numblue=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 0) {
                    numblue = numblue + 1;
                }
            }
            i++;
        }
        return numblue;
    }

    public int rightblueColor (List<Coord> alist ){

        int  numblue=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 0) {
                    numblue = numblue + 1;
                }
            }
            i++;

        }

        return numblue;
    }
    public int upblueColor (List<Coord> alist ){

        int  numblue=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(up(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) == 0) {
                    numblue = numblue + 1;
                }
            }
            i++;

        }

        return numblue;
    }

    public int maxblueColor (List<Coord> alist ){
        int maxNumblue = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumblue = -1;
        }
        else{
            maxNumblue = Math.max(upblueColor(alist),Math.max(downblueColor(alist),Math.max(leftblueColor(alist),rightblueColor(alist))));
        }
        return maxNumblue;
    }


    // pink color
    public int downpinkColor (List<Coord> alist ){

        int  numpink=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 1) {
                    numpink = numpink + 1;
                }
            }
            i++;

        }

        return numpink;
    }
    public int leftpinkColor (List<Coord> alist ){

        int  numpink=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 1) {
                    numpink = numpink + 1;
                }
            }
            i++;

        }

        return numpink;
    }
    public int rightpinkColor (List<Coord> alist ){

        int  numpink=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 1) {
                    numpink = numpink + 1;
                }
            }
            i++;

        }

        return numpink;
    }
    public int uppinkColor (List<Coord> alist ){

        int  numpink=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(up(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) == 1) {
                    numpink = numpink + 1;
                }
            }
            i++;

        }

        return numpink;
    }

    public int maxpinkColor (List<Coord> alist ){
        int maxNumpink = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumpink = -1;
        }
        else{
            maxNumpink = Math.max(uppinkColor(alist),Math.max(downpinkColor(alist),Math.max(leftpinkColor(alist),rightpinkColor(alist))));
        }
        return maxNumpink;
    }



    // green color
    public int downgreenColor (List<Coord> alist ){

        int  numgreen=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 2) {
                    numgreen = numgreen + 1;
                }
            }
            i++;

        }

        return numgreen;
    }
    public int leftgreenColor (List<Coord> alist ){

        int  numgreen=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 2) {
                    numgreen = numgreen + 1;
                }
            }
            i++;

        }

        return numgreen;
    }
    public int rightgreenColor (List<Coord> alist ){

        int  numgreen=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 2) {
                    numgreen = numgreen + 1;
                }
            }
            i++;

        }

        return numgreen;
    }
    public int upgreenColor (List<Coord> alist ){

        int  numgreen=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(up(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) == 2) {
                    numgreen= numgreen + 1;
                }
            }
            i++;

        }

        return numgreen;
    }

    public int maxgreenColor (List<Coord> alist ){
        int maxNumgreen = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumgreen = -1;
        }
        else{
            maxNumgreen = Math.max(upgreenColor(alist),Math.max(downgreenColor(alist),Math.max(leftgreenColor(alist),rightgreenColor(alist))));
        }
        return maxNumgreen;
    }



// yellow


    public int downyellowColor (List<Coord> alist ){

        int  numyellow=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 3) {
                    numyellow= numyellow+ 1;
                }
            }
            i++;

        }

        return numyellow;
    }
    public int leftyellowColor (List<Coord> alist ){

        int  numyellow=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 3) {
                    numyellow = numyellow + 1;
                }
            }
            i++;

        }

        return numyellow;
    }
    public int rightyellowColor (List<Coord> alist ){

        int  numyellow=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 3) {
                    numyellow = numyellow + 1;
                }
            }
            i++;

        }

        return numyellow;
    }
    public int upyellowColor (List<Coord> alist ){

        int  numyellow=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(up(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) == 3) {
                    numyellow= numyellow + 1;
                }
            }
            i++;

        }

        return numyellow;
    }

    public int maxyellowColor (List<Coord> alist ){
        int maxNumyellow = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumyellow= -1;
        }
        else{
            maxNumyellow = Math.max(upyellowColor(alist),Math.max(downyellowColor(alist),Math.max(leftyellowColor(alist),rightyellowColor(alist))));
        }
        return maxNumyellow;
    }

    //red
    public int downredColor (List<Coord> alist )
    {
        int  numred=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 4) {
                    numred= numred+ 1;
                }
            }
            i++;
        }
        return numred;
    }

    public int leftredColor (List<Coord> alist ){

        int  numred=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 4) {
                    numred = numred + 1;
                }
            }
            i++;

        }

        return numred;
    }
    public int rightredColor (List<Coord> alist ){

        int  numred=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 4) {
                    numred = numred + 1;
                }
            }
            i++;
        }

        return numred;
    }
    public int upredColor (List<Coord> alist )
    {
        int  numred=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(up(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) ==4) {
                    numred= numred+ 1;
                }
            }
            i++;
        }
        return numred;
    }

    public int maxredColor (List<Coord> alist ){
        int maxNumred = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumred= -1;
        }
        else{
            maxNumred = Math.max(upredColor(alist),Math.max(downredColor(alist),Math.max(leftredColor(alist),rightredColor(alist))));
        }
        return maxNumred;
    }



    //cyan

    public int downcyanColor (List<Coord> alist ){

        int  numcyan=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(down(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(down(current)) == 5)
                {
                    numcyan= numcyan+ 1;
                }
            }
            i++;

        }

        return numcyan;
    }
    public int leftcyanColor (List<Coord> alist ){

        int  numcyan=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(left(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(left(current)) == 5) {
                    numcyan = numcyan + 1;
                }
            }
            i++;

        }

        return numcyan;
    }
    public int rightcyanColor (List<Coord> alist ){

        int  numcyan=0;
        int i = 0;
        while (i<alist.size()){
            Coord current = floodList.get(i);
            if (inbound(right(current))) {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(right(current)) == 5) {
                    numcyan = numcyan + 1;
                }
            }
            i++;

        }

        return numcyan;
    }

    public int upcyanColor (List<Coord> alist )
    {
        int  numcyan=0;
        int i = 0;
        while (i<alist.size())
        {
            Coord current = floodList.get(i);
            if (inbound(up(current)))
            {
                int currentColor = driver.getColor(alist.get(i));
                if (driver.getColor(up(current)) == 5) {
                    numcyan= numcyan+ 1;
                }
            }
            i++;

        }

        return numcyan;
    }

    public int maxcyanColor (List<Coord> alist ){
        int maxNumcyan = -1;
        if (driver.getColor(alist.get(0))==0){
            maxNumcyan= -1;
        }
        else
        {
            maxNumcyan = Math.max(upcyanColor(alist),Math.max(downcyanColor(alist),Math.max(leftcyanColor(alist),rightcyanColor(alist))));
        }
        return maxNumcyan;
    }


    public int maxColorIndex(List<Coord> alist) {
        int a = maxblueColor(alist);
        int b = maxpinkColor(alist);
        int c = maxgreenColor(alist);
        int d = maxyellowColor(alist);
        int e = maxredColor(alist);
        int f = maxcyanColor(alist);
        int maxNum = -99;
        int maxNumIndices = 0;
        ArrayList<Integer> numList = new ArrayList<>();
        numList.add(a);
        numList.add(b);
        numList.add(c);
        numList.add(d);
        numList.add(e);
        numList.add(f);

        for (int i = 0; i < numList.size(); i++)
        {
            if (numList.get(i) > maxNum)
            {
                maxNum = numList.get(i);
                maxNumIndices = i;
            }
            i++;
        }
        return maxNumIndices;
    }





    //is input cell (specified by coord) on the board?
    public boolean inbound(final Coord coord)
    {
        final int x = coord.x;
        final int y = coord.y;
        final int size = this.driver.size;
        return x > -1 && x < size && y > -1 && y < size;
    }

    //return the coordinate of the cell on the top of the given cell (coord) 
    public Coord up(final Coord coord) {
        return new Coord(coord.x, coord.y-1);
    }
    //return the coordinate of the cell below the given cell (coord)
    public Coord down(final Coord coord) {
        return new Coord(coord.x, coord.y+1);
    }
    //return the coordinate of the cell to the left of the given cell (coord)
    public Coord left(final Coord coord) {
        return new Coord(coord.x-1, coord.y);
    }
    //return the coordinate of the cell to the right of the given cell (coord)
    public Coord right(final Coord coord) {
        return new Coord(coord.x + 1, coord.y);
    }
    //get the color of a cell (coord)
    public int colorOfCoord(final Coord coord) {
        return this.driver.getColor(coord);
    }
}
