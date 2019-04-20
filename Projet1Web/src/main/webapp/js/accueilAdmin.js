

var n1 = document.getElementById('nbArticlePeremption').innerHTML;
console.log(n1);
var cpt1 = 0;
var duree1 = 0.1;
var delta1 = Math.ceil((duree1 * 1000) );
var node1 =  document.getElementById('stockPeremption');
 
function countdown1() {
	node1.innerHTML = ++cpt1;
  
  if( cpt1 < n1 ){
     setTimeout(countdown1, delta1);
  }
}

setTimeout(countdown1, delta1);



var n2 = document.getElementById('nbArticleRupture').innerHTML;
console.log(n2);
var duree2 = 0.1;
var cpt2 = 0;
var delta2 = Math.ceil((duree2 * 1000) );
var node2 =  document.getElementById('stockRupture');
 
function countdown2() {
	node2.innerHTML = ++cpt2;
  
  if( cpt2 < n2 ){
     setTimeout(countdown2, delta2);
  }
}

setTimeout(countdown2, delta2);


var n = document.getElementById('commandesAtraiter').innerHTML;
console.log(n);
var cpt = 0;
var duree = 0.1;
var delta = Math.ceil((duree * 1000) );
var node =  document.getElementById('compteur');
 
function countdown() {
	node.innerHTML = ++cpt;
  
  if( cpt < n ){
     setTimeout(countdown, delta);
  }
}

setTimeout(countdown, delta);



      
