package app.gwt.sample.server;


import app.gwt.sample.shared.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserImpl implements Parser {

    @Override
    public String parseLog(Log log) {
        String tempParsedString="";
        String parameters="";
        String[] paramsArray;
        StringBuffer result = new StringBuffer();
        String patternSelect = "SELECT|UPDATE|DELETE";
        String parametersPattern = "(\\(.*\\))(.*)";
        String questionMark = "\\?";
        int counter=0;
        int startIndex = 0 ;

        Pattern patternFormula = Pattern.compile(patternSelect);
        Matcher matcherFormula = patternFormula.matcher(log.getContent());
        while(matcherFormula.find()) {

            System.out.println("found: "+" : "
                    + matcherFormula.start() + " - " + matcherFormula.end());
             tempParsedString = log.getContent().substring(matcherFormula.start());
            System.out.println("parseeeed "+tempParsedString);
            parameters = tempParsedString.substring(tempParsedString.indexOf("[") + 8, tempParsedString.indexOf("]"));
            tempParsedString = tempParsedString.substring(0,tempParsedString.indexOf("["));
            break;
        }



        if(parameters!=null) {
            paramsArray = parameters.split(",");

            System.out.println("parameters " + parameters);

            Pattern patternParams = Pattern.compile(parametersPattern);
            for (int i=0;i<paramsArray.length;++i) {
                Matcher matcherPatternParms = patternParams.matcher(paramsArray[i]);
                while (matcherPatternParms.find()) {
                    System.out.println("group 1: " + matcherPatternParms.group(2));
                    paramsArray[i]=matcherPatternParms.group(2);
                }

            }

            Pattern patternQuestionMark = Pattern.compile(questionMark);
            Matcher matcherQuestionMark = patternQuestionMark.matcher(tempParsedString);

            while(matcherQuestionMark.find()) {
                System.out.println("found: "+" : "
                        + matcherQuestionMark.start() + " - " + matcherQuestionMark.end());


                //parsedFinalString = tempParsedString.replace(tempParsedString.substring(matcherQuestionMark.start(),matcherQuestionMark.end()),paramsArray[counter]);
                result.append(tempParsedString.substring(startIndex,matcherQuestionMark.end()).
                        replace(tempParsedString.substring(matcherQuestionMark.start(),matcherQuestionMark.end()),paramsArray[counter]));
                ++counter;
                startIndex = matcherQuestionMark.end();
            }
            //doklejmy czesc ktora zostala uceita
            if(startIndex!=tempParsedString.length()){
                System.out.println("uciaaaal");
                result.append(tempParsedString.substring(startIndex,tempParsedString.length()));
            }


        }

        System.out.println("koncowy "+result.toString());

        return result.toString();
    }
}
