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
            tempParsedString = log.getContent().substring(matcherFormula.start());
            parameters = tempParsedString.substring(tempParsedString.indexOf("[") + 8, tempParsedString.indexOf("]"));
            tempParsedString = tempParsedString.substring(0,tempParsedString.indexOf("["));
            break;
        }



        if(parameters!=null) {
            paramsArray = parameters.split(",");
            Pattern patternParams = Pattern.compile(parametersPattern);
            for (int i=0;i<paramsArray.length;++i) {
                Matcher matcherPatternParms = patternParams.matcher(paramsArray[i]);
                while (matcherPatternParms.find()) {
                    paramsArray[i]=matcherPatternParms.group(2);
                }

            }

            Pattern patternQuestionMark = Pattern.compile(questionMark);
            Matcher matcherQuestionMark = patternQuestionMark.matcher(tempParsedString);

            while(matcherQuestionMark.find()) {

                result.append(tempParsedString.substring(startIndex,matcherQuestionMark.end()).
                        replace(tempParsedString.substring(matcherQuestionMark.start(),matcherQuestionMark.end()),paramsArray[counter]));
                ++counter;
                startIndex = matcherQuestionMark.end();
            }
            //doklejmy czesc ktora zostala uceita
            if(startIndex!=tempParsedString.length()){
                result.append(tempParsedString.substring(startIndex,tempParsedString.length()));
            }
            
        }

        return result.toString();
    }
}
