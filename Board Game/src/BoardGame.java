public class BoardGame {
        private char[][] gameBoard;
        private HashDictionary dictionary;
        private int boardSize;
        private int emptyPositions;
        private int maxLevels;

        public BoardGame(int board_size, int empty_positions, int max_levels) {
            boardSize = board_size;
            emptyPositions = empty_positions;
            maxLevels = max_levels;
            gameBoard = new char [boardSize][boardSize];
            //fill the board with empty values
            for(int i = 0; i < boardSize; i++) {
                for(int j = 0; j < boardSize; j++) {
                    gameBoard[i][j] = 'g';
                }
            }
        }

        public HashDictionary makeDictionary() {
            //initialize dictionary
            dictionary = new HashDictionary(9997);
            return dictionary;
        }

        public int isRepeatedConfig(HashDictionary dict) {
            //represent content of gameBoard as string
            String board = this.boardConfiguration();

            //check if string is in dictionary, return score if it is
            if(dict.getScore(board) != -1) {
                return dictionary.getScore(board);
            }
            return -1;
        }

        public void putConfig(HashDictionary dict, int score) {
            //represent the board as a string and add it to the dictionary
            String board = this.boardConfiguration();

            Configuration data =  new Configuration(board, score);

            try {
                dictionary.put(data);
            } catch (DictionaryException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void savePlay(int row, int col, char symbol) {
            //store play to game board
            gameBoard[row][col] = symbol;
        }

        public boolean positionIsEmpty(int row, int col) {
            //check if current position is empty
            if(gameBoard[row][col] == 'g') {
                return true;
            }
            return false;
        }

        public boolean tileOfComputer(int row, int col) {
            //check for computer tile
            if(gameBoard[row][col] == 'o') {
                return true;
            }
            return false;
        }

        public boolean tileOfHuman(int row, int col) {
            //check for human tile
            if(gameBoard[row][col] == 'b') {
                return true;
            }
            return false;
        }

        public boolean wins(char symbol) {
            //check if object with symbol wins
            for(int i = 0; i < boardSize; i++) {
                //check the first item in the row to see if it has symbol, if it does, loop through that row
                if(gameBoard[i][0] == symbol) {
                    for(int j = 0; j < boardSize; j++) {
                        if(gameBoard[i][j] != symbol) {
                            break;
                        }
                        if(j == boardSize-1) {
                            if(gameBoard[i][j] == symbol) {
                                return true;
                            }
                        }
                    }
                }
                //Check the first item in the column to see if it has symbol, if it does, loop through the column
                if(gameBoard[0][i] == symbol) {
                    for(var j = 0; j < boardSize; j++) {
                        if(gameBoard[j][i] != symbol) {
                            break;
                        }
                        if(j == boardSize-1) {
                            if(gameBoard[j][i] == symbol) {
                                return true;
                            }
                        }
                    }
                }

                //check to see if symbol forms diagonals on the board
                if(i == 0 && gameBoard[i][i] == symbol) {
                    for(int j = 1; j < boardSize; j++) {
                        if(gameBoard[j][j] != symbol) {
                            break;
                        }
                        if(j == boardSize-1) {
                            if(gameBoard[j][j] == symbol) {
                                return true;
                            }
                        }
                    }
                }

                if(i==0 && gameBoard[i][boardSize-1] == symbol) {
                    for(int j = 1; j < boardSize; j++) {
                        if(gameBoard[j][boardSize-1-j] != symbol) {
                            break;
                        }
                        if(j== boardSize-1) {
                            if(gameBoard[j][boardSize-1-j] == symbol) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public boolean isDraw(char symbol, int empty_positions) {
            //check if game is a draw
            int[] coordinates = new int[2];
            int empty_space = 0;
            //track empty spaces left on the board
            for(int i = 0; i < boardSize; i++) {
                for(int j = 0; j < boardSize; j++) {
                    if(gameBoard[i][j] == 'g') {
                        empty_space++;
                    }
                }
            }

            //if the empty space is larger than allowed number of empty spaces, it's not a draw
            if(empty_space > emptyPositions) {
                return false;
            } else {
                if(empty_positions == 0 || empty_space == emptyPositions) {
                    return true;
                }
                //check if current symbol has an adjacent tile, if not, then it is a draw (no more space left)
                for(int i = 0; i < boardSize; i++) {
                    for(int j = 0; j < boardSize; j++) {
                        if(gameBoard[i][j] == 'g') {
                            coordinates[0] = i;
                            coordinates[1] = j;
                            if(this.hasAdjacent(symbol, coordinates)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }

        }

        private boolean hasAdjacent(char symbol, int[] position) {
            //check if there is an adjacent tile with symbol
            if((position[0]-1 < boardSize && position[0]-1 > -1) && (position[1]-1 < boardSize && position[1]-1 > -1) && gameBoard[position[0]-1][position[1]-1] == symbol) {
                return true;
            } else if((position[0]-1 < boardSize && position[0]-1 > -1) && gameBoard[position[0]-1][position[1]] == symbol) {
                return true;
            } else if((position[0]-1 < boardSize && position[0]-1 > -1) &&(position[1]+1 < boardSize && position[1]+1 > -1) && gameBoard[position[0]-1][position[1]+1] == symbol) {
                return true;
            } else if(position[1]-1 < boardSize && position[1]-1 > -1 && gameBoard[position[0]][position[1]-1] == symbol) {
                return true;
            } else if(position[1]+1 < boardSize && position[1]+1 > -1 && gameBoard[position[0]][position[1]+1] == symbol) {
                return true;
            } else if((position[0]+1 < boardSize && position[0]+1 > -1) && (position[1]-1 < boardSize && position[1]-1 > -1) && gameBoard[position[0]+1][position[1]-1] == symbol) {
                return true;
            } else if((position[0]+1 < boardSize && position[0]+1 > -1) && gameBoard[position[0]+1][position[1]] == symbol){
                return true;
            } else if((position[0]+1 < boardSize && position[0]+1 > -1) && (position[1]+1 < boardSize && position[1]+1 > -1) && gameBoard[position[0]+1][position[1]+1] == symbol) {
                return true;
            }
            return false;

        }

        public int evalBoard(char symbol, int empty_positions) {
            //returns score for current board
            if(this.wins('o')) {
                return 3;
            } else if(wins('b')) {
                return 0;
            } else if(this.isDraw(symbol, empty_positions)) {
                return 2;
            } else {
                return 1;
            }
        }

        private String boardConfiguration() {
            //represent the current board as a string
            String board="";
            for(int i = 0; i < boardSize; i++) {
                for(int j=0; j < boardSize; j++) {
                    board+=gameBoard[i][j];
                }
            }
            return board;
        }
    }

