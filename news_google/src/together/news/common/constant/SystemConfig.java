package together.news.common.constant;

public class SystemConfig {
	public static EDTypeEnum DEFAULT_EDTYPE = EDTypeEnum.JSON;
	public static String EXCHANGE_DATA_TYPE = ".json";
	
	public static enum EDTypeEnum{
		JSON,PROTOBUF,XML,THRIFT,MSG_PACK
	}
	public static void init(){
		initEDType();
	}
	private static void initEDType() {
		switch(DEFAULT_EDTYPE){
		case JSON: EXCHANGE_DATA_TYPE=".json";break;
		case PROTOBUF: EXCHANGE_DATA_TYPE=".proto";break;
		case XML: ;
		case THRIFT: ;
		case MSG_PACK: EXCHANGE_DATA_TYPE=".mpack";
		default: ;
		}
	}
}
