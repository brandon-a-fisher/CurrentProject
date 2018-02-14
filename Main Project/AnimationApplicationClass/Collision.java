public class Collision {
    public void checkHitObstacle (Obstacles[] obstaclesArray, Player player){
        for (int obstacle = 0; obstacle < obstaclesArray.length; obstacle++){
            if (player.getYPosition == obstaclesArray[obstacle].getYPosition &&
                player.getXPosition == obstaclesArray[obstacle].getXPosition)
                    obstaclesArray[obstacle].setIsAlive = false;
        }
    }
    public void checkHitCollectible (Collectible[] collectibleArray, Player player){
        for (int collectible = 0; collectible < collectibleArray.length; collectible++){
            if (player.getYPosition == collectibleArray[collectible].getYPosition &&
                player.getXPosition == collectibleArray[collectible].getXPosition)
                    collectibleArray[collectible].setIsAlive = false;
    }
}
