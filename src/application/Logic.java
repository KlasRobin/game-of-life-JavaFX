package application;

public class Logic {
	
    Cell[][] tempCells;
    Cell[][] tempCells2;
    int count;
    
    public Logic(){
    	tempCells = new Cell[50][50];
    	for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				tempCells[i][j] = new Cell(false, 0);
			}
		}
    	tempCells2 = new Cell[50][50];
    	for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				tempCells2[i][j] = new Cell(false, 0);
			}
		}
    	count = 0;
    	
    }

	public void setNeighbours(Board b) {
		Cell[][] currCells=b.getCells();
		Cell[][] newCells;
		count++;
		if(count%2==0){
			newCells = tempCells;
		}else{
			newCells = tempCells2;
		}
		

		killCells(newCells);
		for (int y = 0; y < currCells[0].length; y++) {

			for (int x = 0; x < currCells.length; x++) {
				
				boolean topLeft = (!isOutOfBounds(y-1, x-1, currCells)) && currCells[y - 1][x - 1].isAlive();
				boolean top = (!isOutOfBounds(y-1, x, currCells)) && currCells[y - 1][x].isAlive();
				boolean topRight = (!isOutOfBounds(y-1, x+1, currCells)) && currCells[y - 1][x+1].isAlive();
				boolean left = (!isOutOfBounds(y, x-1, currCells)) && currCells[y][x - 1].isAlive();
				boolean right = (!isOutOfBounds(y, x+1, currCells)) && currCells[y][x + 1].isAlive();
				boolean bottomLeft = (!isOutOfBounds(y+1, x-1, currCells)) && currCells[y + 1][x - 1].isAlive();
				boolean bottom = (!isOutOfBounds(y+1, x, currCells)) && currCells[y + 1][x].isAlive();
				boolean bottomRight = (!isOutOfBounds(y+1, x+1, currCells)) && currCells[y + 1][x + 1].isAlive();

				Cell current = currCells[y][x];

				if (topLeft)
					currCells[y][x].addNeighbour();
				if (topRight)
					currCells[y][x].addNeighbour();
				if (top)
					currCells[y][x].addNeighbour();
				if (left)
					currCells[y][x].addNeighbour();
				if (right)
					currCells[y][x].addNeighbour();
				if (bottom)
					currCells[y][x].addNeighbour();
				if (bottomLeft)
					currCells[y][x].addNeighbour();
				if (bottomRight)
					currCells[y][x].addNeighbour();

				if (current.isAlive()) {
					if (current.getNeighbours() < 2) {
						newCells[y][x].setAlive(false);
					} else if (current.getNeighbours() == 2 || current.getNeighbours() == 3) {
						newCells[y][x].setAlive(true);
					} else if (current.getNeighbours() > 3) {
						newCells[y][x].setAlive(false);
					}

				} else {
					if (current.getNeighbours() == 3) {
						newCells[y][x].setAlive(true);
					}

				}

			}

		}

		b.setCells(newCells);
	}

	private void killCells(Cell[][] cells) {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				cells[i][j].setAlive(false);
				cells[i][j].setNeighbours(0);
			}
		}
	}

	private boolean isOutOfBounds(int x, int y, Cell[][] c) {

		if ((x  < 0) || (y<0) || (y>c[0].length-1 || x>c.length-1)) {
			return true;
		}
		return false;

	}
	
	
		
	

}
