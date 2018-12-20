
def Random():Int={
	var r = scala.util.Random
	var rand = r.nextInt(12)+2
	return rand
}

def CardVal(Num:Int):String={
	var card = ""
	Num match{
		case 2 => card="2"
		case 3 => card="3"
		case 4 => card="4"
		case 5 => card="5"
		case 6 => card="6"
		case 7 => card="7"
		case 8 => card="8"
		case 9 => card="9"
		case 10 => card="10"
		case 11 => card="J"
		case 12 => card="Q"
		case 13 => card="K"
		case 14 => card="A"
	}
	return card
}

def Deal():Int={
	var dealF = Random()
	var dealS = Random()
	var cardfaceF = CardVal(dealF)
	var cardfaceS = CardVal(dealS)
	if(dealF>10 && dealF<14){
		dealF=10
	}
	if(dealF==14){
		dealF=11
	}
	if(dealS>10 && dealS<14){
		dealS=10
	}
	if(dealS==14){
		dealS=11
	}
	var total = dealF+dealS
	var show = cardfaceF+" "+cardfaceS
	print(show+"   Total: "+total)
	return total
}

def HitStick(dealtot:Int):Int={
	var totalH = dealtot
	while(totalH<22){
		println("")
		print("Hit or Stand? H/S?: ")
		var choice = scala.io.StdIn.readLine()
		if(choice=="H" || choice=="h"){
			var newCard=Random()
			print("Card: "+CardVal(newCard))
			if(newCard>10 && newCard<14){
				newCard=10
			}
			if(newCard==14){
				newCard=11
			}
			totalH += newCard
			if(totalH>21 && newCard==11){
				totalH-=11
				totalH+=1
			}
			print("   Total: "+totalH)
		}
		if(choice=="S"||choice=="s"){
			println("Stand: "+totalH)
			totalH +=100
		}
	}
	if(totalH<122){
		totalH-=100
	}
	if(totalH<0){
		println("")
		println("BUST!")
	}
	return totalH
}

print("Player: ")
var playdeal=Deal()
var playerScore=HitStick(playdeal)

println("")

print("Dealer: ")
var dealdeal=Deal()
var dealScore=HitStick(dealdeal)

println("")

if(playerScore>dealScore){
	println("Player Wins!")
}
if(dealScore>playerScore){
	println("Dealer Wins!")
}
if(dealScore==playerScore){
	println("It's a Tie!")
}