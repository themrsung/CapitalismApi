package jbs.capitalismapi.types.config;

public class Messages {
    public Messages() {}

    // THIS IS ALSO USED AS A SAVE TYPE. ONLY DEFINE VARIABLES. NO FUNCTIONS OR METHODS.

    public String unknownError = "알 수 없는 오류가 발생했습니다. 오류가 지속될 경우 관리자에게 문의해주세요.";

    public String insufficientFunds = "잔고가 부족합니다.";
    public String insufficientStocks = "주식이 부족합니다.";
    public String insufficientVotes = "의결권있는 주식이 부족합니다.";
    public String insufficientPermissions = "권한이 부족합니다.";
    public String insufficientArguments = "입력하신 항목이 부족합니다.";
    public String insufficientItems = "아이템이 부족합니다.";
    public String unrecognizedCommand = "명령어를 인식할 수 없습니다.";

    public String entityNotFound = "경제주체를 찾을 수 없습니다.";
    public String playerNotFound = "플레이어를 찾을 수 없습니다.";
    public String companyNotFound = "회사를 찾을 수 없습니다.";
    public String bankNotFound = "은행을 찾을 수 없습니다.";
    public String stockNotFound = "주식을 찾을 수 없습니다.";
    public String stockNotListedInExchange = "해당 주식이 해당 거래소에 상장되어있지 않습니다.";
    public String stockOrderNotFound = "주식 주문을 찾을 수 없습니다.";
    public String securitiesExchangeNotFound = "증권거래소를 찾을 수 없습니다.";

    public String paymentSuccessful = "%s 명의로 %r에게 %a를 보냈습니다.";
    public String stockPaymentSuccessful = "%s 명의로 %r에게 %c %q주를 보냈습니다.";
    public String bankDepositSuccessful = "%s 명의로 %b에 %a를 입금했습니다.";
    public String bankWithdrawalSuccessful = "%s 명의로 %b에서 %a를 출금했습니다. (출금수수료: %f)";

    public String invalidMoney = "유효하지 않은 금액입니다.";
    public String invalidQuantity = "유효하지 않은 수량입니다.";
    public String invalidNumber = "유효하지 않은 금액입니다.";
    public String invalidAction = "유효하지 않은 행동입니다.";
    public String invalidItemType = "유효하지 않은 아이템 유형입니다.";

    public String commandOnlySupportedForPlayers = "플레이어만 이용할 수 있는 명령어입니다.";
    public String commandOnlySupportedForConsoles = "콘솔에서만 이용할 수 있는 명령어입니다.";

    public String teleportSucceeded = "텔레포트가 성공적으로 실행되었습니다.";
    public String teleportDestinationInvalid = "도착지가 유효하지 않습니다.";
    public String spawnPointSet = "스폰 지점이 변경되었습니다.";

    public String balanceOfEntity = "%e의 타이머: %m (%mc)";

    public String[] companyCommandHelpMessages = {
            "/company 행동",
            "",
            "행동:",
            "- new: 새로운 회사를 설립합니다.",
            "- info: 회사 정보를 조회합니다.",
            "- manage: 회사를 관리합니다.",
            "- search: 회사 이름으로 코드를 검색합니다.",
            "- offers: 고용 요청을 관리합니다.",
            "- retire: 회사에서 은퇴합니다.",
            "- balance: 회사의 잔고를 확인합니다.",
            "- pay: 회사 명의로 송금합니다.",
            "- stocks: 회사 주식을 관리합니다.",
            "- bank: 회사 은행 계좌를 관리합니다.",
            "- vote: 회사 명의로 투표합니다."
    };

    public String[] companyCommandNewHelpMessages = {
            "/company new 유형, 코드, 이름, 자본금, 주식수",
            "새 회사를 설립합니다.",
            "",
            "유형: normal/bank/stockexchange",
            "코드: 3자리 이하의 알파벳 조합 (대소문자 미구분)",
            "이름: @mnl글자 이하의 이름 (컬러코드 지원)",
            "자본금: 회사 유형에 따라 다름",
            "주식수: 0 < x <= %msc"
    };

    public String[] companyCommandInfoHelpMessages = {
            "/company info 검색어",
            "회사 정보를 확인합니다.",
            "",
            "검색어: 회사코드 -> 회사명 순서로 검색"
    };

    public String[] companyCommandManageHelpMessages = {
            "/company manage 코드",
            "회사를 관리합니다.",
            "",
            "코드: 정확한 회사코드"
    };

    public String[] companyCommandSearchHelpMessages = {
            "/company search 이름",
            "회사코드를 찾습니다.",
            "",
            "이름: 회사명 또는 부분명"
    };

    public String[] companyCommandOffersHelpMessages = {
            "/company offers",
            "나에게 온 초대를 관리합니다."
    };

    public String[] companyCommandRetireHelpMessages = {
            "/company retire 코드",
            "회사에서 은퇴합니다.",
            "",
            "코드: 정확한 회사코드"
    };

    public String[] companyCommandBalanceHelpMessages = {
            "/company balance 코드",
            "회사 잔고를 확인합니다.",
            "",
            "코드: 회사코드 또는 부분코드"
    };

    public String[] companyCommandPayHelpMessages = {
            "/company pay 코드 수신인 금액",
            "회사 명의로 송금합니다.",
            "",
            "코드: 정확한 회사코드",
            "수신인: 회사코드 -> 유저네임 -> 회사명 순서로 검색",
            "금액: 금액 또는 수식(예: 2.7h -> 2.7시간)"
    };

    public String[] companyCommandStocksHelpMessages = {
            "/company stocks 코드",
            "회사 명의의 주식을 관리합니다.",
            "",
            "코드: 정확한 회사코드"
    };

    public String[] companyCommandBankHelpMessages = {
            "/company bank 코드",
            "회사 명의의 은행 계좌를 관리합니다.",
            "",
            "코드: 정확한 회사코드"
    };

    public String[] companyCommandVoteHelpMessages = {
            "/company vote 코드",
            "회사를 통해 투표 가능한 주주총회를 보고, 투표합니다.",
            "",
            "코드: 정확한 회사코드"
    };
    public String[] companyCommandBondHelpMessages = {
            "/company bond 코드",
            "채권/채무를 관리합니다.",
            "",
            "코드: 정확한 회사코드"
    };

    public String companyTypeInvalid = "회사 유형이 올바르지 않습니다.";
    public String companyCodeInvalid = "회사코드를 확인해주세요.";
    public String companyCodeInUse = "회사코드가 사용중입니다.";
    public String companyNameInvalid = "회사명을 확인해주세요.";
    public String companyCapitalTooLow = "자본금이 최소자본금보다 낮습니다.";
    public String companyCreated = "회사가 설립되었습니다.";
    public String[] companyInformation = {
            "회사 정보",
            "%s - %n (%sp)",
            "%desc",
            "",
            "시가총액: %mc",
            "순자산: %nw",
            "주식수: %sc주 / 액면가: %fc",
            "대표자: %ceo / 이사회: %d명 / 직원수: %e명 ",
            "대표자 일급: %dcp / 이사 일급: %ddp / 직원 일급: %dep"
    };

    public String[] additionalBankInformation = {
            "일 이자율: %dir% / 출금수수료: %wfr%"
    };

    public String[] additionalSecuritiesExchangeInformation = {
            "매수주문 수수료: %bfr% / 매도주문 수수료: %sfr%"
    };

    public String[] additionalCommodityExchangeInformation = {
            "매수주문 수수료: %bfr% / 매도주문 수수료: %sfr%"
    };


    public String jobOfferListHeader = "회사 초대 목록:";
    public String jobOfferInfo = "- %c에서 %p로 초대함";
    public String noJobOfferFromCompany = "%c에서 온 초대가 없습니다.";
    public String jobOfferAccepted = "초대를 수락했습니다.";
    public String jobOfferRejected = "초대를 거절했습니다.";

    public String notAMemberOfCompany = "%c에 소속되어있지 않습니다.";
    public String retiredFromCompany = "%c에서 은퇴했습니다.";
    public String symbolOfCompanyIs = "%c의 회사코드는 %s입니다.";
    public String[] companyCommandManagePropertyHelpMessages = {
            "/c manage 코드 setting 설정 값",
            "회사 설정을 관리합니다.",
            "",
            "설정:",
            "- dailyceopay: CEO 일급",
            "- dailydirectorpay: 이사 일급",
            "- dailyemployeepay: 직원 일급",
            "- listedstocks: 상장된 주식 목록*",
            "- liststock 코드: 해당 주식 상장*",
            "- deliststock 코드: 해당 주식 상장폐지*",
            "- buyerfeepercent 값: 매수수수료*",
            "- sellerfeepercent 값: 매도수수료*",
            "- dailyinterestratepercent 값: 일 이자율**",
            "- withdrawalfeepercent 값: 출금수수료율**",
            "",
            "*증권거래소 전용 설정",
            "**은행 전용 설정"
    };
    public String companySettingChanged = "회사 설정이 변경되었습니다.";
    public String companyNotASecuritiesExchange = "증권거래소가 아닙니다.";
    public String companyAlreadyListed = "이미 상장된 회사입니다.";
    public String companyListed = "상장되었습니다.";
    public String companyDelisted = "상장폐지되었습니다.";
    public String companyNotABank = "은행이 아닙니다.";

    public String companyDescriptionTooLong = "회사 소개가 너무 깁니다.";

    public String stocksListHeader = "%e에 상장된 주식:";
    public String stocksListEntry = "- %s: %p";

    public String directorListHeader = "임원 목록 (%c명):";
    public String directorInfo = "- %n";

    public String playerNotADirector = "플레이어가 이사회에 소속되어있지 않습니다.";
    public String playerAlreadyDirector = "플레이어가 이미 이사회에 소속되어있습니다.";
    public String playerInvitedAsDirector = "플레이어가 이사회에 초대되었습니다.";
    public String playerRemovedFromBoard = "플레이어가 이사회에서 해임되었습니다.";

    public String employeeListHeader = "직원 목록 (%c명):";
    public String employeeInfo = "- %n";

    public String playerNotAnEmployee = "플레이어가 직원이 아닙니다.";
    public String playerAlreadyEmployee = "플레이어가 이미 직원입니다.";
    public String playerInvitedAsEmployee = "플레이어가 직원으로 초대되었습니다.";
    public String playerFired = "플레이어가 해고되었습니다.";

    public String playerAlreadyInvited = "이미 플레이어에게 보낸 초대가 있습니다.";

    public String companyListHeader = "회사 목록:";
    public String companyListEntry = "%s - %n (%nw)";

    public String[] payCommandHelpMessages = {
            "/pay 수신인 금액",
            "본인 명의로 송금합니다.",
            "",
            "수신인: 회사코드 -> 유저네임 -> 회사명 순서로 검색",
            "금액: 금액 또는 수식(예: 2.7h -> 2.7시간)"
    };

    public String[] stocksCommandHelpMessages = {
            "/stocks 행동",
            "주식을 관리합니다.",
            "",
            "행동:",
            "- buy: 주식 매수주문을 접수합니다.",
            "- sell: 주식 매도주문을 접수합니다.",
            "- cancel: 주식 주문을 취소합니다.",
            "- orders: 미체결주문을 확인합니다.",
            "- my: 보유주식을 확인합니다.",
            "- give: 주식을 다른 경제주체에게 양도합니다."
    };

    public String stockPortfolioHeader = "%o의 보유주식 (%q):";
    public String stockPortfolioEntry = "- %s %q주 - 현재가: %p / 매입가: %c / 수익률: %r%";
    public String stockPortfolioFooter = "포트폴리오 총 가치: %v";

    public String stockOrdersHeader = "%s의 미체결주문 (%q):";
    public String stockOrderInfo = "- %s %q주 %bos - 가격: %p / 주문번호: %i";

    public String stockOrderCancelled = "주식 주문이 취소되었습니다.";
    public String allStockOrdersCancelled = "모든 주식 주문이 취소되었습니다.";

    public String stockBuyOrderPlaced = "%s 명의로 주식 매수 주문이 접수되었습니다. (주문번호: %i)";
    public String stockSellOrderPlaced  = "%s 명의로 주식 매도 주문이 접수되었습니다. (주문번호: %i)";

    public String cannotSellSelfOwnedSharesOfCompanyWhenStockRetireMeetingIsOpen = "자사주 소각 주주총회가 열려있는 회사는 자사주를 매도할 수 없습니다.";
    public String cannotGiveSelfOwnedSharesOfCompanyWhenStockRetireMeetingIsOpen = "자사주 소각 주주총회가 열려있는 회사는 자사주를 양도할 수 없습니다.";

    public String[] voteCommandHelpMessages = {
            "/vote 회사코드 행동",
            "주주통회에서 투표합니다.",
            "",
            "행동:",
            "- info: 현재 열려있는 주주통회 정보를 조회합니다.",
            "- yes: 찬성에 투표합니다.",
            "- no: 반대에 투표합니다.",
            "- open: 주총을 개최합니다."
    };

    public String[] voteCommandNewHelpMessages = {
            "/vote 회사코드 new 유형 내용 - 본인 명의",
            "/c vote 대변하는회사코드 대상회사코드 new 유형 내용 - 회사 명의",
            "",
            "유형:",
            "- hire_ceo: CEO 신규 선임 / 내용: 새 CEO 이름",
            "- fire_ceo: CEO 해임 / 내용: 없음",
            "- liquidate: 회사 청산 / 내용: 없음",
            "- dividend: 현금배당 / 내용: 주당배당금(DPS)",
            "- stock_split: 주식분할 / 내용: 주당 신주발행수량",
            "- stock_issue: 자사주 발행 / 내용: 신규발행주식수",
            "- stock_retire: 자사주 소각 / 내용: 소각주식수",
            "- other: 기타 사안 / 내용: 직접 입력"
    };

    public String[] meetingInformation = {
            "주주총회 안내",
            "안건: %a",
            "",
            "찬성주식수: %y / 반대주식수: %n (찬성률: %ar%)",
            "투표주식수: %cv / 총주식수: %tv (투표율: %vr%)",
            "",
            "투표주식수의 %prc% 이상, 총주식수의 %prt% 이상 찬성 시 통과"
    };

    public String noCurrentlyOpenMeeting = "현재 열려있는 주주총회가 없습니다.";
    public String invalidMeetingType = "유효하지 않은 주주총회 유형입니다.";
    public String meetingAlreadyExists = "이미 주주총회가 진행중입니다.";
    public String noVotableSharesExist = "의결권 있는 주식이 없습니다.";
    public String ceoIsNotNull = "CEO가 공석이 아닙니다.";
    public String ceoIsAlreadyNull = "CEO가 이미 공석입니다.";
    public String cannotLiquidateCompanyWithActiveBankAccounts = "은행 잔고가 있는 회사는 청산할 수 없습니다.";
    public String cannotLiquidateCompanyWithOutstandingStockOrders = "주식 미체결 주문이 있는 회사는 청산할 수 없습니다.";
    public String cannotLiquidateCompanyWithStocksInPortfolio = "보유주식이 있는 회사는 청산할 수 없습니다.";
    public String cannotPayDividendOverCompanyBalance = "총 배당금은 회사의 잔고보다 클 수 없습니다.";
    public String shareCountAfterMeetingTooHigh = "안건 통과 시 최대주식수가 서버 한도보다 높습니다.";
    public String shareCountAfterMeetingTooLow = "언건 통과 시 주식수가 1보다 낮습니다.";
    public String insufficientSelfOwnedShares = "자사주가 충분하지 않습니다.";
    public String meetingCreated = "주주통회가 개최되었습니다.";
    public String meetingVoted = "주주통회 의결이 완료되었습니다.";

    public String[] bankCommandHelpMessages = {
            "/bank 행동",
            "은행 업무를 처리합니다.",
            "",
            "핼동:",
            "- info: 은행 정보(금리, 출금수수료)를 조회합니다.",
            "- deposit: 특정 은행에 입금합니다.",
            "- withdraw: 특정 은행에서 출금합니다.",
            "- balance: 은행 잔고를 확인합니다."
    };

    public String bankBalanceListHeader = "은행별 잔고:";
    public String bankBalance = "%e 명의로 %b(%bc)에 예치된 금액: %m (%mc)";
    public String bankBalanceListFooter = "합계: %m (%mc)";
    public String bankInformation = "%b(%bc): 일 이자율: %ir% / 출금수수료: %wfr%";

    public String bankListHeader = "은행 목록:";

    public String[] premiumInformation = {
            "%p의 프리미엄 여부: %ip",
            "%p의 프리미엄 만료일: %ed"
    };

    public String premiumTimeAdded = "%p의 프리미엄 시간을 추가했습니다.";
    public String premiumTimeRemoved = "%p의 프리미엄 시간을 제거했습니다.";
    public String playerIsNotPremium = "프리미엄 유저가 아닙니다.";
    public String playerIsLifetimePremium = "영구 프리미엄 유저입니다.";
    public String playerSetToLifetimePremium = "%p는 영구 프리미엄 유저로 설정되었습니다.";
    public String playerSetToNotPremium = "%p를 일반 유저로 강등했습니다.";

    public String[] bondCommandHelpMessages = {
            "/bond 행동",
            "채권/채무를 관리합니다.",
            "",
            "핼동:",
            "- offers: 채무/채권 요청을 관리합니다.",
            "- lend: 돈을 빌려줍니다.",
            "- borrow: 돈을 빌립니다.",
            "- list: 내 채권을 조회합니다.",
            "- give: 내 채권을 양도합니다."
    };

    public String lendOffersHeader = "%e에게 빌려주기로 한 요청 (%q):";
    public String borrowOffersHeader = "%e에게 빌려달라고 한 요청 (%q)";
    public String bondOfferInfo = "%c가 %d에게 %p를 일%dir%에 빌려줌. (총 상환액: %tv, 만기: %dte일)";
    public String bondOfferAccepted = "채권/채무 관계가 등록되었으며, 원금이 이체되었습니다.";
    public String bondOfferSent = "채권/채무 요청이 전송되었습니다.";
    public String allBondOffersDeclined = "모든 채권/채무 요청을 거절했습니다.";
    public String bondOfferDeclined = "채권/채무 요청을 거절했습니다.";
    public String bondOfferNotFound = "해당 경제주체와 주고받은 요청이 없습니다.";
    public String targetDoesNotHaveEnoughFundsToBorrowToDebtor = "채권자가 원금을 지급할 능력이 없습니다.";
    public String bondOfferAlreadyInPlaceBetweenEntities = "채권/채무 요청이 이미 존재합니다.";
    public String bondTotalValueCannotBeNegative = "채권의 만기상환금액은 음수일 수 없습니다.";
    public String bondNotFound = "채권을 찾을 수 없습니다.";
    public String bondTransferred = "채권을 이체했습니다.";
    public String bondPortfolioHeader = "%e 명의의 채권 포트폴리오(%q): ";
    public String bondPortfolioEntry = "- %id / 채무자: %d / 금액: %v / 만기: %e";
    public String bondPortfolioFooter = "포트폴리오 평가액: %v";

    public String[] netWorthOfEntity = {
            "%e의 자산: %a (%ac)",
            "%e의 부채: %l (%lc)",
            "%e의 순자산: %nw (%nwc)"
    };

    public String[] moneySupply = {
            "서버 통화량:",
            "- M1: %m1 (%m1c)",
            "- M2: %m2 (%m2c)"
    };

    public String netWorthLeaderboardHeader = "순자산 상위목록:";
    public String netWorthLeaderboardEntry = "[%n] %e: %w";

    public String noItemInHand = "손에 든 아이템이 없습니다.";

    public String noBuyOrdersForItem = "해당 아이템에 대한 매수주문이 없습니다.";
    public String quickSellSuccessful = "총 %q개에 대해 매도주문을 접수했습니다.";
    public String[] tradeCommandHelpMessages = {
            "/trade 행동",
            "아이템을 거래합니다.",
            "",
            "행동:",
            "- buy: 공개시장에서 아이템 매수",
            "- sell: 공개시장에서 아이템 매도",
            "- cancel: 아이템 주문 취소",
            "- orders: 아이템 미체결주문 조회",
            "- give: 아이템 양도",
            "- deliveries: 미수령 아이템 조회",
            "- receive: 미수령 아이템 인수",
            "- deny: 미수령 아이템 인도거부"
    };

    public String deniedDeliveryOfAllUnreceivedItems = "모든 미수령 아이템을 인도거부했습니다.";
    public String deniedDeliveryOfItem = "%i %q개의 인도를 거부했습니다.";
    public String receivedItems = "%i %q개를 인도했습니다.";
    public String insufficientUnreceivedItems = "미수령 아이템이 부족합니다.";
    public String startingDeliveryOfUnreceivedItems = "미수령 아이템 인도를 시작합니다.";
    public String finishedDeliveryOfUnreceivedItems = "미수령 아이템 인도가 완료되었습니다.";
    public String unreceivedItemsListHeader = "미수령 아이템 (%c항목):";
    public String unreceivedItemsListEntry = "%m: %q개";
    public String errorWhileTakingItemsFromInventory = "인벤토리에서 아이템을 제거하던 중 오류가 발생했습니다. 제거된 아이템은 내 미수령 아이템에 추가되었습니다.";
    public String itemsSentToPlayer = "%p에게 %i %q개를 전송했습니다.";
    public String itemOrderListHeader = "아이템 미체결주문:";
    public String itemOrderListEntry = "- %i %q개 %bos (가격: %p): %id";
    public String allItemOrdersCancelled = "모든 아이템 미체결주문이 취소되었습니다.";
    public String itemOrderNotFound = "아이템 주문을 찾을 수 없습니다.";
    public String itemOrderCancelled = "아이템 주문이 취소되었습니다.";
    public String commodityExchangeNotFound = "상품거래소를 찾을 수 없습니다.";
    public String itemBuyOrderPlaced = "아이템 매수주문이 접수되었습니다. (ID: %id)";
    public String itemSellOrderPlaced = "아이템 매도주문이 접수되었습니다. (ID: %id)";
    public String itemNotSupported = "지원되지 않는 아이템입니다.";





}
