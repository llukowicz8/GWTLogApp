package app.gwt.sample.client;

import app.gwt.sample.server.Parser;
import app.gwt.sample.server.ParserImpl;
import app.gwt.sample.shared.Log;
import org.junit.Assert;

public class ParserTest {



        @org.junit.Test
        public void testParserOne() {
            Parser parser = new ParserImpl();
            Assert.assertNotNull(parser);

            String formula = "32123  TRACE  [btpool0-0] kodo.jdbc.SQL - <t 10272075, conn 18292272> [0 ms] batching prepstmnt 32961443 " +
                    "UPDATE TESTSOFT.TB_NUMBERING SET IS_COPY_OF_NUMBER_ID = ? " +
                    "WHERE IS_COPY_OF_NUMBER_ID = ? [params=(null) null, (long) 1328126975]";

            String expectedResult = "UPDATE TESTSOFT.TB_NUMBERING SET IS_COPY_OF_NUMBER_ID =  null " +
                    "WHERE IS_COPY_OF_NUMBER_ID =  1328126975 ";

            Log log = new Log(1,formula);

            Assert.assertEquals(expectedResult,parser.parseLog(log));

            }

    @org.junit.Test
    public void testParserTwo() {
        Parser parser = new ParserImpl();
        Assert.assertNotNull(parser);

        String formula = "290033  TRACE  [btpool0-0] kodo.jdbc.SQL - <t 10272075, conn 18292272> [30 ms] executing prepstmnt 24117950 " +
                "SELECT t1.ID, t1.RESOURCE_TYPE, t1.LAST_UPDATE, t1.CREATED_BY, t1.CREATED_ON, t1.IDENTIFIER_PERSISTENT, t1.LAST_MODIFIED_BY, " +
                "t1.LAST_MODIFIED_ON, t1.TYPE_ATTRIBUTE, t0.IS_AUTO_RESERVED, t0.BNP, t0.C, t0.CRM_ORDER_NUMBER, t0.D, t0.DESCENT_NUM, t0.GRANULARITY, " +
                "t0.IDENTIFIER_AB_PERSISTENT, t0.IDENTIFIER_DEST_PERSISTENT, t0.IS_MAIN, t0.IS_MOVED, t0.IS_VOIP, t0.M, t0.P, t0.Q, t0.RFS_SERVICE_HOST_OCCUP, " +
                "t0.S, t0.SECONDARY_DESTINATION_NUM, t0.IDENTIFIER_PERSISTENT, t0.STATE, t0.U, t0.WR_DATE FROM TESTSOFT.TB_NUMBERING t0, TESTSOFT.TB_RESOURCE t1 " +
                "WHERE ((t0.STATE <> ? AND t0.STATE <> ? OR t0.SECONDARY_DESTINATION_NUM = ?) AND t0.NUMBER_RANGE_ID = ?) AND t1.RESOURCE_TYPE = 4 AND t0.ID = t1.ID " +
                "[params=(int) 0, (int) 1, (int) 1, (long) 1300053803, (int) 4]";

        String expectedResult =
                "SELECT t1.ID, t1.RESOURCE_TYPE, t1.LAST_UPDATE, t1.CREATED_BY, t1.CREATED_ON, t1.IDENTIFIER_PERSISTENT, t1.LAST_MODIFIED_BY, " +
                "t1.LAST_MODIFIED_ON, t1.TYPE_ATTRIBUTE, t0.IS_AUTO_RESERVED, t0.BNP, t0.C, t0.CRM_ORDER_NUMBER, t0.D, t0.DESCENT_NUM, t0.GRANULARITY, " +
                "t0.IDENTIFIER_AB_PERSISTENT, t0.IDENTIFIER_DEST_PERSISTENT, t0.IS_MAIN, t0.IS_MOVED, t0.IS_VOIP, t0.M, t0.P, t0.Q, t0.RFS_SERVICE_HOST_OCCUP, " +
                "t0.S, t0.SECONDARY_DESTINATION_NUM, t0.IDENTIFIER_PERSISTENT, t0.STATE, t0.U, t0.WR_DATE FROM TESTSOFT.TB_NUMBERING t0, TESTSOFT.TB_RESOURCE t1 " +
                "WHERE ((t0.STATE <>  0 AND t0.STATE <>  1 OR t0.SECONDARY_DESTINATION_NUM =  1) AND t0.NUMBER_RANGE_ID =  1300053803) AND t1.RESOURCE_TYPE = 4 AND t0.ID = t1.ID ";

        Log log = new Log(1,formula);

        Assert.assertEquals(expectedResult,parser.parseLog(log));

    }


}
