var handleViewMode = function (formUuid) { 

	$("htmlform").each(function (index, currentForm) {
		if ($(currentForm).attr("formuuid") == formUuid) {

			

			/* display only '.toggleContainers' fields with observations */
			/* See toggleContainers.js for more documentation */
			$(currentForm).find(".toggleContainer").each(function (index, toggleContainer) {
				if (!($(toggleContainer).find('.value').text() == "")) {
					$(toggleContainer).show();
				}
			});

			/* remove buttons */
			$(".addRemoveButtons").hide();
			$(currentForm).find('.addEntry').remove();
			$(currentForm).find('.removeEntry').remove();


			/* Iterate through each section of the current HTML form */
			$(currentForm).find("section").each(function (indexS, currentSection) {

				var emptyFieldset = -1;
				var hideSection = false;

				$(currentSection).find("fieldset").each(function (indexF, currentFieldset) {

					var hideFieldset = false;

					if ($(currentFieldset).find("p").length > 1) {
						$(currentFieldset).find("*").addClass("multiple");
						$(currentFieldset).find("h3").addClass("indented");
					}

					/* Hide elements with class value and text null */
					$(currentFieldset).find(".value").each(function (indexV, element) {
						/* Hide "p" section when value is empty */
						if ($(element).text() == "") {
							$(element).closest("p").hide();
							$(element).closest("p").prev("h3").hide();
							$(element).addClass("emptyValue");
						}
					});

					/*  */
					$(currentFieldset).find("p").prev("h3").addClass("questionLabel");

					/* hide the parent "p" section when Obs have emptyValue */
					$(currentFieldset).find(".emptyValue").closest("p").hide();
					$(currentFieldset).find(".emptyValue").closest("p").prev("h3").hide();

					var emptyValue = -1;
					/* hide the fieldset when all "p" contain ".emptyValue" */
					$(currentFieldset).find("p").each(function (indexP, currentP) {
						if ($(currentP).find('.emptyValue').val() == "") {
							emptyValue = emptyValue + 1;	
						}

						if ((emptyValue == indexP)) {
							hideFieldset = true;
						} else {
							hideFieldset = false;
						}
					});

					if (hideFieldset) {
						$(currentFieldset).addClass("emptyFieldset");
					}

					/* if all fieldsets of the current section are hidden, define an empty fieldset */
					if ($(currentFieldset).hasClass("emptyFieldset")){
						emptyFieldset = emptyFieldset + 1;
					}
					/* if all fieldsets are empty, define the hideSection */
					if(emptyFieldset == indexF) {
						hideSection = true;
					} else {
						hideSection = false;
					}

				});

				if (hideSection) {
					$(currentSection).addClass("emptySection");
				}

			});

			substituteTable(currentForm);
		}
	})
}

var substituteTable = function (currentForm) {

	/* iterate through each <table> of the forms */
	var hideTable = false;
	$(currentForm).find("fieldset").find("table").each(function (indexT, currentTable) {

		var emptyTd = -1;
		$(currentTable).find("td").each(function (index, td){
			/* remove if the <td> has emptyValue */
			if ($(td).find(".emptyValue").text()) {
				$(td).find(".emptyValue").closest("p").addClass("emptyValue");
				emptyTd = emptyTd + 1;
			}

			if (emptyTd == index) {
				hideTable = true;
			} else {
				hideTable = false;
			}
		});

		/* If the table contains only empty "td"s then set its class as ".emptyTable" */
		if (hideTable) {
			$(currentTable).addClass('emptyTable');
		}
	});


	/* replacing the tables by a list of "p" wrapped in a "span" */
	$("table").each(function(index, table) {

		var span = $("<span>");

		$(span).addClass("fromTable");

		$("th", this).each(function() {
			var title = $("<span>");
			title.append(this.innerHTML);
			$(title).addClass("title");
			span.append(title);
		});
		$("td", this).each(function(){
			span.append(this.innerHTML);
		});
		if ($(table).hasClass("emptyTable")) { 
			$(span).addClass("emptyTable");
		}
		$(table).replaceWith(span);
	});

}
