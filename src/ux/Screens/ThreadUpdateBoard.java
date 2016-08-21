package ux.Screens;

import game.Game;
import network.Client;
import network.messages.GameRequest;

public class ThreadUpdateBoard implements Runnable{
	protected ScrGame scrGame = null;
	protected boolean running = true;
	public ThreadUpdateBoard(ScrGame scrGame) {
		// TODO Auto-generated constructor stub
		this.scrGame = scrGame;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Send request for board and update the board every 2 seconds
		while(running){
			try{
				System.out.println("Board Refreshed on board -" + this.scrGame.getName());
				Thread.sleep(1000);
				Client.client.send(new GameRequest(scrGame.game.name), (p)->scrGame.networkGame(p));
				if(scrGame.game.turn.getName().equals(Client.client.getUsername())){
					//if it is the client's turn again then stop this thread
					running = false;
					break;
				}
			}catch(Exception e){
				if (e instanceof InterruptedException){
					running = false;
					break;
				}else{
					e.printStackTrace();
				}
			}
		}
	}
	
}
