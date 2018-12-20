
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

var aces = 0
var aceVal=0

def Deal():Int={
	var dealF = Random()
	var dealS = Random()
	var cardfaceF = CardVal(dealF)
	var cardfaceS = CardVal(dealS)
	var show = cardfaceF+" "+cardfaceS
	println("Hand: "+show)
	
	if(dealF>10 && dealF<14){
		dealF=10
	}
	if(dealF==14){
		print("Ace High or Low? H/L?: ")
		var dealFacechoice = scala.io.StdIn.readLine()
		if(dealFacechoice=="H" || dealFacechoice=="h"){
			dealF=11
			aceVal+=11
		}
		if(dealFacechoice=="L" || dealFacechoice=="l"){
			dealF=1
			aceVal+=1
		}
		aces+=1
	}
	if(dealS>10 && dealS<14){
		dealS=10
	}
	if(dealS==14){
		print("Ace High or Low? H/L?: ")
		var dealSacechoice = scala.io.StdIn.readLine()
		if(dealSacechoice=="H" || dealSacechoice=="h"){
			dealS=11
			aceVal+=11
		}
		if(dealSacechoice=="L" || dealSacechoice=="l"){
			dealS=1
			aceVal+=1
		}
		aces+=1
	}
	var total = dealF+dealS
	
	print("   Total: "+total)
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
			println("Card: "+CardVal(newCard))
			if(newCard>10 && newCard<14){
				newCard=10
			}
			if(newCard==14){
				aces+=1
				totalH -= aceVal
				println("What do you want your "+aces+" aces to be?")
				aceVal=0
				var i=1
				while(i <= aces){
					print("Ace "+i+" High or Low? H/L?: ")
					var acechoice = scala.io.StdIn.readLine()
					if(acechoice=="H" || acechoice=="h"){
						aceVal+=11
					}
					if(acechoice=="L" || acechoice=="l"){
						aceVal+=1
					}
					i+=1
				}
				newCard=aceVal
				
			}
			totalH += newCard
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

if(playerScore<0 && dealScore<0){
	println("Nobody wins! You both BUST!")
	sys.exit
}
if(playerScore>dealScore){
	println("Player Wins!")
}
if(dealScore>playerScore){
	println("Dealer Wins!")
}
if(dealScore==playerScore){
	println("It's a Tie!")
}