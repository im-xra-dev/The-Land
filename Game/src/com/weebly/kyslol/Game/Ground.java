package com.weebly.kyslol.Game;

public class Ground {
	int colour;
	String biome;
	int Structurex, Structurey;
	String Structure, all;
	
	
	public Ground(int colour, String biome, String Structure, int Structurey, int Structurex){
		this.colour = colour;
		this.biome = biome;
		this.Structurey = Structurey;
		this.Structurex = Structurex;
		this.Structure = Structure;
//		this.x = x;
//		this.y = y;
//		all = "[Colour(" + colour + ")Structure(" + Structure + ")Structurex("	
//		 + Structurex + ")Structurey(" + Structurey + ")Biome(" + biome + ")]";

	}
	public String getData(){
		return all;
	}

	
}
