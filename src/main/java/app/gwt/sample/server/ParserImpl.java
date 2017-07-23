package app.gwt.sample.server;


import app.gwt.sample.shared.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserImpl implements Parser {

    @Override
    public String parseLog(Log log) {
        String params="";
        String parsedString="";
        StringBuffer sb = new StringBuffer();
        String patternSelect = "SELECT|UPDATE|DELETE";
        String questionMark = "\\?";

        Pattern patternFormula = Pattern.compile(patternSelect);
        Matcher matcherFormula = patternFormula.matcher(log.getContent());
        while(matcherFormula.find()) {

            System.out.println("found: "+" : "
                    + matcherFormula.start() + " - " + matcherFormula.end());
             parsedString = log.getContent().substring(matcherFormula.start());
            System.out.println("parseeeed "+parsedString);
            params = parsedString.substring(parsedString.indexOf("[") + 8, parsedString.indexOf("]"));
            parsedString = parsedString.substring(0,parsedString.indexOf("["));
            break;
        }

        String[] paramsArray={null};

        if(params!=null) {
            paramsArray = params.split(",");

            System.out.println("params " + params);
            String parametersPattern = "(\\(.*\\))(.*)";
            Pattern patternParams = Pattern.compile(parametersPattern);
            for (int i=0;i<paramsArray.length;++i) {
                Matcher matcherPatternParms = patternParams.matcher(paramsArray[i]);
                while (matcherPatternParms.find()) {
                    System.out.println("group 1: " + matcherPatternParms.group(2));
                    paramsArray[i]=matcherPatternParms.group(2);
                }

            }

            Pattern patternQuestionMark = Pattern.compile(questionMark);
            Matcher matcherQuestionMark = patternQuestionMark.matcher(parsedString);

            int counter=0;
            int startIndex = 0 ;
            while(matcherQuestionMark.find()) {
                System.out.println("found: "+" : "
                        + matcherQuestionMark.start() + " - " + matcherQuestionMark.end());


                //parsedFinalString = parsedString.replace(parsedString.substring(matcherQuestionMark.start(),matcherQuestionMark.end()),paramsArray[counter]);
                sb.append(parsedString.substring(startIndex,matcherQuestionMark.end()).
                        replace(parsedString.substring(matcherQuestionMark.start(),matcherQuestionMark.end()),paramsArray[counter]));
                ++counter;
                startIndex = matcherQuestionMark.end();
            }
            //doklejmy czesc ktora zostala uceita
            if(startIndex!=parsedString.length()){
                System.out.println("uciaaaal");
                sb.append(parsedString.substring(startIndex,parsedString.length()));
            }


        }

        System.out.println("koncowy "+sb.toString());

        return sb.toString();
    }
}
