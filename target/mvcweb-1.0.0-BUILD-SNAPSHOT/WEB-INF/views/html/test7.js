/**
 * 
 */
var test7UserAnswersObject = {};
        var test7CorrectAnswersObject = {};

        var test7UnansweredQuestionsDataObject = {};

        var wronglyAnsweredQsDataForReference = {};
        
        test1Counter = 0;
  	  test2Counter = 0;
  	  test3Counter = 0;
  	  test4Counter = 0;
  	  test5Counter = 0;
  	  test6Counter = 0;
  	  
  	  
  	  test1QuestionsData = [];
  	  test2QuestionsData = [];
  	  test3QuestionsData = [];
  	  test4QuestionsData = [];
  	  test5QuestionsData = [];
  	  test6QuestionsData = [];	
function renderingLogic(data, qId) {
            //reset the info/success/danger informative section
            $('#refSection').empty();
            $("#refSection").removeClass('alert alert-danger glyphicon glyphicon-remove');
            $("#refSection").removeClass('glyphicon glyphicon-thumbs-up alert alert-success');
            $("#refSection").removeClass('glyphicon glyphicon-info-sign alert alert-info');

            //Do not delete the following is reseting the user choices, so need to use with caution
            $('input:radio:checked').each(function () {
                $('input:radio:checked')[0].checked = false;
            });

            //empty the previous question
            $('#question').empty();
            var questionNumber = test7Counter + 1;
            //append the new question
            $('#question').append('<br/><b>Question<br/></br> #' + questionNumber + "/" + test7QuestionsData.length + ")</b>&nbsp;&nbsp;" + data.question
                + '<br/> <br/> <b>Options</b>');

            // set the hidden varaibles
            $('#qID').val(qId);
            $('#correctAnswer').val(data.correctAnswer);
            $('#referenceLink').val(data.referenceLink);

            //populate the options with text
            jQuery.each(data.options, function (i, val) {
                $("#radioLabel" + (i + 1)).text(val);
            });

            //using the following to set the right values while navigating back and forth between prev and next questions
            //this is for test mode need to verify in debug again to add the condition
            var userChosenOption = test7UserAnswersObject[qId];
            var correctAnswer = test7CorrectAnswersObject[qId];
            if (!(userChosenOption == null)) {

                $('#' + userChosenOption).prop('checked', true);
                $("input[type=radio]").attr('disabled', true);

                if (userChosenOption == correctAnswer) {
                    $("#refSection").addClass('glyphicon glyphicon-thumbs-up alert alert-success');
                    $("#refSection").append('<strong> Good Job!</strong>');
                } else {
                    var referenceLinksArray = document.getElementById('referenceLink').value;
                    var referenceLinks = referenceLinksArray.split(",");
                    $("#refSection").addClass('glyphicon glyphicon-remove alert alert-danger ');

                    $("#refSection").append('<b>Incorrect Option chosen earlier!To get more information on this please</b><mark> <u><a target="_blank" href="/mvcweb/resources/' + referenceLinks[0] + '.pdf">' + referenceLinks[1] + '</a></u></mark>');
                }
            }

            //this is being used in read only mode
            var correctOptionId = Number(data.correctAnswer);
            if (readOnlyMode) {
                $("input[type=radio]").attr('disabled', true);
                $('#' + correctOptionId).prop('checked', true);

                var referenceLinksArray = data.referenceLink;
                var referenceLinks = referenceLinksArray.split(",");
                $("#refSection").addClass('glyphicon glyphicon-info-sign alert alert-info');
                $("#refSection").append('<b>To get more information on this please</b><mark> <u><a target="_blank" href="/mvcweb/resources/' + referenceLinks[0] + '.pdf">' + referenceLinks[1] + '</a></u></mark>');
            }

            //need to see whether these are being used anymore
            $('#optionsContainer').show();
            $('.tab-content').show();
            $('#content').empty().html(data);

        }
