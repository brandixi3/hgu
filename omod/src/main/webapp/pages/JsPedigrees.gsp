

<%
		
		ui.includeCss("hgu", "JsPedigrees.css");
	    ui.includeJavascript("hgu", "jspedigrees.nocache.js");
	    ui.includeJavascript("hgu", "start-pedigrees.js");
	%>

<%= ui.resourceLinks() %>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <title>jsPedigrees</title>

   
  </head>

  <body>
	<div style="background-color:#F0F0F0">
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <h1>jsPedigrees</h1>
    <center><i>Updated 30-August-2013</i></center>
     <b>Instructions</b><br>
     
	<style>
ol.a {
    list-style-type: upper-arab;
    position: relative;
  	left: 30px;
}
ol.b {
    list-style-type: upper-roman;
    position: relative;
  	left: 40px;
}
ul.c {
    list-style-type: circle;
    position: relative;
  	left: 40px;
}
</style>
     
     
     
    <ol class="a">
    	<li>Click members of the pedigree and an Edit menu will pop up that allows you to:</li>
    		<ul class="c">
    			<li>Add members of the pedigree related to the selected person</li>
    			<li>Change the sex or disease status of the selected person</li>
    			<li>Delete the selected person</li>
    		</ul>
    	<li>The <b>Edit</b> menu allows un-doing and re-doing changes.</li>
    	<li>Click "solve" to solve the pedigree (note that this can take a long time if the peidgree is large). </li>
    		<ol class="b">
    			<li>The solution will appear in the box below the "solve" button.</li>
    			<li>The solution will list the 4 possible modes of inheritance</li>
    				<ul class="c">
    					<li>If the mode is possible, it will list genotypes for each individual that are
    						consistent with the pedigree and the mode. </br>Symbols can be found in the tables
    						below.</li>
    					<li>If the mode is not possible, it will say "Not possible".</li>
    				</ul>
    		</ol>
    </ol>
    <table>
    	<tr>
    		<td colspan=2 id="jsPedigreesContainer"> </td>
		</tr>
		<tr>
		    <td colspan=2>
				<button type="button" onClick="document.getElementById('solution').value=pedexSolvePedigree()">Solve</button><br>
				<textarea id="solution" rows=20 cols=50></textarea>
			</td>
		</tr>
		<tr>
			<td>Autosomal Recessive
				<table border=1>
					<tr><th>Genotype</th><th>Phenotype</th></tr>
					<tr><td>A A</td><td>Normal</td></tr>
					<tr><td>A a</td><td>Normal</td></tr>
					<tr><td>a a</td><td>Affected</td></tr>
				</table>
			</td>
			<td>Autosomal Dominant
				<table border=1>
					<tr><th>Genotype</th><th>Phenotype</th></tr>
					<tr><td>B B</td><td>Affected</td></tr>
					<tr><td>B b</td><td>Affected</td></tr>
					<tr><td>b b</td><td>Normal</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>Sex-linked Recessive
				<table border=1>
					<tr><th>Genotype</th><th>Phenotype</th></tr>
					<tr><td>XD XD</td><td>Normal Female</td></tr>
					<tr><td>XD Xd</td><td>Normal Female</td></tr>
					<tr><td>Xd Xd</td><td>Affected Female</td></tr>
					<tr><td>XD Y</td><td>Normal Male</td></tr>
					<tr><td>Xd Y</td><td>Affected Male</td></tr>
				</table>
			</td>
			<td>Sex-linked Dominant
				<table border=1>
					<tr><th>Genotype</th><th>Phenotype</th></tr>
					<tr><td>XE XE</td><td>Affected Female</td></tr>
					<tr><td>XE Xe</td><td>Affected Female</td></tr>
					<tr><td>Xe Xe</td><td>Normal Female</td></tr>
					<tr><td>XE Y</td><td>Affected Male</td></tr>
					<tr><td>Xe Y</td><td>Normal Male</td></tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<hr>
This was developed by:
<ul>
	<li><b>Original Pedigree drawing code (Pelican)</b> by <a href="http://www.mrc-bsu.cam.ac.uk/personal/frank/software/pelican/">
		Frank Dudbridge</a>. Dudbridge F, Carver T, Williams GW. Pelican: Pedigree Editor for Linkage 
		Computer Analysis. <i>Bioinformatics</i> 20:2327-8</li>
	<li><b>Pedigree solving code, javascript conversion, etc.</b> <a href="http://intro.bio.umb.edu/BW/">Brian
		White</a>.
	
</ul>
  </body>

