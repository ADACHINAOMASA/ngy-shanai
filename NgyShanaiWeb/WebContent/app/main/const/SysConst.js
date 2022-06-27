/**
 * アプリケーション全体の定数
 */
(function () {
	// 定数をjava側と共有できる仕組みを考えるか
	var kengen = {
		kanrisha: 'KANRISHA',
	    gyomuTanto: 'GYOMU_TANTO',
	    eigyoTanto: 'EIGYO_TANTO',
	    supportTanto: 'SUPPORT_TANTO',
	};
	
	var company = {
	    name: '',
	    abbrName: '',
	    nameEn: '',
	    abbrNameEn: '',
	
	    url: 'http://www.nikkei-metal.co.jp',
	    copyright: 'Copyright (C) 2016 Nikkei Metal Co. Ltd. All Rights Reserved.',
	    copyright1: 'Copyright',
	    copyright2: '2016',
	    copyright3: 'Nikkei Metal Co. Ltd. All Rights Reserved.'
	};
	
	var system = {
	    name: 'CM3スケジューリングシステム',
	    nameEn: 'CM3 Scheduling System',
	    abbrName: 'cm3sch',
	    appName: 'Cm3Sch'
	};
	
	var uigrid = {
	    paginationPageSizes: [10, 20, 50, 100],
	    paginationPageSize: 10
	};
	
	var alert = {
	    timeout: 1000 * 60,
	    successTime: 8000,
	    warningTime: 10000,
	    errorTime  : 11000,
	    defaultTime: 5000,
	}
	
	var flag = {
	    on: '1',
	    off: '0'
	};
	
	var message = {
		expanding:'すべてを展開中・・・',
		closing:'すべてを省略中・・・',
		getting:'取得中・・・',
		saving:'保存中・・・',
		loading:'Loading...',
		
		inputError:"入力内容にエラーが残っています。",
	}
	
		
	//コードは必ず、MitsumoriJotai.javaと一致すること。
	var mitsumoriJotai ={
		SAGYO_CHU:"1", 
		
		SHONIN_IRAI_CHU:"2", 
		SHONIN_ZUMI:"3",
		
		SEIZO_SHONIN_IRAI_CHU:"4", 
		SEIZO_SHONIN_ZUMI:"5",
		
		KESSAI_IRAI_CHU:"6", 
		KESSAI_ZUMI:"7",
		
		KAKUTEI:"8",
	};
	
	//コードは必ず、Keishiki.javaと一致すること。
	var keishiki = {
		pdf: '1',
	    excel: '2',
	    pdfExt: '.pdf',
	    excelExt: '.xlsx',
	};
	
	//コードは必ず、ShoninJokyo.javaと一致すること。
	var shoninJokyo = {
		miShonin   : '1',
		shoninChu  : '2',
		shoninZumi : '3',
		all        : '9',
	};
	
	//コードは必ず、KakuteiFlg.javaと一致すること。
	var kakuteiFlg = {
		kakutei : '1',
		dorafuto : '0',
	};
	
	//コードは必ず、SeizoKonyuBunrui1.javaと一致すること。
	var seizoKonyuBunrui1 = {
		seizohin : '1',
		konyuhin : '2',
		naiseichukanbuhin : '3',
		gaichukakohin : '4',
	};
	
	//コードは必ず、AppConst.javaと一致すること。
	var appConst = {
		gamenKbn: {
			gamenNew: '00',
			gamenTeiseiDorafuto: '10',
			gamenTeiseiKakutei: '11',
			gamenKaitei: '20',
		},
		tabKbn: {
			kakoKeisanKakohi:'0', 
			kakoKeisanZairyoItazai:'1', 
			kakoKeisanZairyoKatazai:'2',
			kakoKeisanHozai:'3',
			kakoKeisanNaiseiChukanBuhin:'4',
			kakoKeisanKakotsukiAlumiZai:'5',
			kakoKeisanGaichuKakohin:'6',
		},
	};
	
	//コードは必ず、KakushuMasterKbn.javaと一致すること。
	var kakushuMasterKbn = {
		//既存
		ZairyoBunrui: '07',
		YotoBunrui: '08',
		TehaiKbn: '11',
		Sagyokotei: '17',
		
		//新規
		FormatPtn: '29',
		MeisaiType: '30',
		ShoninJokyo: '31',
		MitsumoriJokyo: '32',
		ShisakuKbn: '33',
		MeisaiGyoPtn: '34',
		Stage: '35',
		IraiNaiyo: '36',
		SeizoKonyuBurui1: '37',
		SeizoKonyuBurui2: '38',
		ZaikoKanri: '39',
		TankuBuhinFlg: '40',
		SagyoSetsubi: '41',
		ZairyoTehaiKbn: '42',
		ZairyoKbn: '43',
		MitsumoriJotai: '44',
		Statsu: '45',
	};
	
	//コードは必ず、FormatPtn.javaと一致すること。
	var formatPtn = {
		ONE_PAGE_UCHIWAKE_NASHI : '1',
		ONE_PAGE_UCHIWAKE_ARI : '2',
		MULTI_PAGE_UCHIWAKE_NASHI : '3',
		MULTI_PAGE_UCHIWAKE_ARI : '4',
		
		ONE_PAGE_UCHIWAKE_NASHI_MAX : 10,
		ONE_PAGE_UCHIWAKE_ARI_MAX : 5,
		MULTI_PAGE_MAX:67,
		
	};
	//コードは必ず、MeisaiGyoPtn.javaと一致すること。
	var meisaiGyoPtn = {
		MEISAI_GYO:'1',
		SHOKEI_GYO:'2',
		COMMENT_GYO:'3',
		MEMO_GYO:'4',
		KAIGYO_GYO:'5',
		NEBIKI_GYO:'6',
	}
	
	
	var buhin = {
			kensakuInfo	: 'BuhinKensakuInfo',
			tourokuInfo	: 'BuhinInfo',
			ichiran: {
				title				: '部品一覧',
				id					: 'BuhinIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'BuhinIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'BuhinIchiranGridState'
			},
			kensaku: {
				title				: '部品検索',
				id					: 'BuhinKensaku'
			},
	};
	
	var tantosha = {
		kensakuInfo     : 'TantoshaKensakuInfo',
		tourokuInfo     : 'TantoshaInfo',
		ichiran: {
			title				: '担当者先一覧',
			id					: 'TantoshaIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'TantoshaIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'TantoshaIchiranGridState'
		},
	};
	
	var torihikisaki = {
		// 使用区分_得意先
		kbnTokuisaki: {
		    on: '1',
		    off: '0',
		},
		// 使用区分_仕入先
		kbnShiresaki: {
		    on: '1',
		    off: '0',
		},
	}
	
	var shiresaki = {
			kensakuInfo     : 'TorihikisakiKensakuInfo',
			tourokuInfo     : 'TorihikisakiInfo',
			ichiran: {
				title				: '仕入一覧',
				id					: 'ShiresakiIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'ShiresakiIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'ShiresakiIchiranGridState'
			},
	};
	
	var tokuisaki = {
		kensakuInfo     : 'TorihikisakiKensakuInfo',
		tourokuInfo     : 'TorihikisakiInfo',
		ichiran: {
			title				: '得意先一覧',
			id					: 'TorihikisakiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'TorihikisakiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'TorihikisakiIchiranGridState'
		},
	};
	
	var gaichusaki = {
			kensakuInfo     : 'TorihikisakiKensakuInfo',
			tourokuInfo     : 'TorihikisakiInfo',
				
			ichiran: {
				title				: '外注加工先一覧',
				id					: 'GaichusakiIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'GaichusakiIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'GaichusakiIchiranGridState'
			},
	};
	
	var yoto = {
		kensakuInfo     : 'YotoKensakuInfo',
		tourokuInfo     : 'YotoInfo',
		ichiran: {
			title				: '用途一覧',
			id					: 'YotoIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'YotoIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'YotoIchiranGridState'
		},
	};
	
	var hinmoku = {
			kensakuInfo     : 'KakoKeisanIchiranKensakuInfo',
			tourokuInfo     : 'KakoKeisanInfo',
			ichiran: {
				title				: '品目一覧',
				id					: 'HinmokuIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'HinmokuIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'HinmokuIchiranGridState'
			},
	};
	
	var mitsumori = {
		    MEISAI_NUM1: 10,
		    MEISAI_NUM2: 5,
		    
		    MAX_PRINT:10,
		    MAX_PRINT_EXCEL:5,
		    
		    juchuWatch:false,
		    
		    fileName: '見積書',
		    
		    kensakuInfo: 'MitsumoriIchiranKensakuInfo',
		    torokuInfo: 'MitsumoriInfo',
		    kihonInfo: 'MitsumoriInfo',
		    mitsumoriInfo: 'MitsumoriInfo',

		    meisaiInfo: 'MitsumoriMsiInfo',
		    uchiwakeInfo: 'MitsumoriUchiwakeInfo',
		    tempFileInfo: 'MitsumoriTempFileInfo',
		    shoninKhnInfo: 'MitsumoriShoninKhnInfo',
		    printInfo: 'MitsumoriPrintInfo',
		    bikoInfo: 'BikoInfo',
		    shoninInfo: 'MitsumoriShoninInfo',
		    juchuInfo: 'JuchuIchiranMeisaiInfo',
		    juchuKensakuInfo: 'JuchuKensakuInfo',

		    ichiran: {
		        title: '見積一覧',
		        id: 'MitsumoriIchiran',
		        
		        isSaiKensaku: true,
		        
		        searchStorageEnable: false,
		        searchStorageName: 'MitsumoriIchiranKensakuJokenState',
		        
		        gridStorageEnable: true,
		        gridStorageName: 'MitsumoriIchiranKensakuGridState'
		    },
		    toroku: {
		        title: '見積登録',
		        id: 'MitsumoriToroku',
		        
		        torokuStorageEnable: false,
		        torokuStorageName: 'MitsumoriTorokuState',
		        gridStorageEnable: true,
		        gridStorageName: 'MitsumoriTorokuJuchuGridState'
		    },
		    shonin: {
		        title: '承認ワークフロー',
		        id: 'MitsumoriShoninWorkflow',
		        shoninStorageEnable: true,
		        shoninStorageName: 'MitsumoriShoninWorkflowState',
		    },
		    fukuseiToroku: {
		        title: '複製登録',
		        id: 'FukuseiToroku',
		    }
	};
	
	var hinmokubetsuChokurohi = {
			kensakuInfo     : 'HinmokubetsuChokurohiKensakuInfo',
			tourokuInfo     : 'HinmokubetsuChokurohiInfo',
			ichiran: {
				title				: '単価一覧（品目別直浪費一覧）',
				id					: 'HinmokubetsuChokurohiIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'HinmokubetsuChokurohiIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'HinmokubetsuChokurohiIchiranGridState'
			},
	};
	
	var kakoKeisan = {
			kensakuInfo	: 'KakoKeisanIchiranKensakuInfo',
			tourokuInfo	: 'KakoKeisanInfo',			
			fileName	: '加工計算書',
			MAX_PRINT	: 5,
			
			ichiran: {
				title				: '加工計算一覧',
				id					: 'KakoKeisanIchiran',
		        
		        isSaiKensaku: true,
		       		        
				searchStorageEnable	: true,
				searchStorageName	: 'KakoKeisanIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'KakoKeisanIchiranGridState'
			},
			toroku: {
				title				: '加工計算登録',
				id					: 'KakokeisanToroku',
				torokuStorageEnable	: true,
		        torokuStorageName	: 'KakokeisanTorokuState',
			},
	};
	
	var kakoKeisanGaichuKakohin = {
			kensakuInfo     : 'KakoKeisanGaichuKakohinInfo',
			tourokuInfo     : 'KakoKeisanGaichuKakohinInfo',
			ichiran: {
				title				: '外注加工品',
				id					: 'KakoKeisanGaichukakohinIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'KakoKeisanGaichukakohinIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'KakoKeisanGaichukakohinIchiranGridState'
			},
			toroku: {
				title				: '外注加工品登録',
				id					: 'KakoKeisanGaichukakohinToroku',
				torokuStorageEnable	: true,
		        torokuStorageName	: 'KakoKeisanGaichukakohinTorokuState',
			},
	};
	
	var kakoKeisanHozai = {
		kensakuInfo     : 'KakoKeisanHozaiInfo',
		tourokuInfo     : 'KakoKeisanHozaiInfo',
		ichiran: {
			title				: '補材',
			id					: 'KakoKeisanHozaiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanHozaiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanHozaiIchiranGridState'
		},
		toroku: {
			title				: '補材登録',
			id					: 'KakoKeisanHozaiToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanHozaiTorokuState',
		},
	};
	
	var kakoKeisanKakohi = {
		kensakuInfo     : 'KakoKeisanKakohiInfo',
		tourokuInfo     : 'KakoKeisanKakohiInfo',
		ichiran: {
			title				: '加工費情報',
			id					: 'KakoKeisanKakohiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanKakohiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanKakohiIchiranGridState'
		},
		toroku: {
			title				: '加工費登録',
			id					: 'KakoKeisanKakohiToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanKakohiTorokuState',
		},
	};
	
	var kakoKeisanKakotsukiAlumiZai = {
		kensakuInfo     : 'KakoKeisanKakotsukiAlumiZaiInfo',
		tourokuInfo     : 'KakoKeisanKakotsukiAlumiZaiInfo',
		ichiran: {
			title				: '加工付アルミ材',
			id					: 'KakoKeisanKakotsukiAlumiZaiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanKakotsukiAlumiZaiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanKakotsukiAlumiZaiIchiranGridState'
		},
		toroku: {
			title				: '加工付アルミ材登録',
			id					: 'KakoKeisanKakotsukiAlumiZaiToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanKakotsukiAlumiZaiTorokuState',
		},
	};
	
	var kakoKeisanNaiseiChukanBuhin = {
		kensakuInfo     : 'KakoKeisanNaiseiChukanBuhinInfo',
		tourokuInfo     : 'KakoKeisanNaiseiChukanBuhinInfo',
		ichiran: {
			title				: '内製中間部品',
			id					: 'KakoKeisanNaiseiChukanBuhinIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanNaiseiChukanBuhinIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanNaiseiChukanBuhinIchiranGridState'
		},
		toroku: {
			title				: '内製中間部品登録',
			id					: 'KakoKeisanNaiseiChukanBuhinToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanNaiseiChukanBuhinTorokuState',
		},
	};
	
	var kakoKeisanZairyoItazai = {
		kensakuInfo     : 'KakoKeisanZairyoItazaiInfo',
		tourokuInfo     : 'KakoKeisanZairyoItazaiInfo',
		ichiran: {
			title				: '材料費（板材）',
			id					: 'KakoKeisanZairyoItazaiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanZairyoItazaiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanZairyoItazaiIchiranGridState'
		},
		toroku: {
			title				: '材料費（板材）登録',
			id					: 'KakoKeisanZairyoItazaiToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanZairyoItazaiTorokuState',
		},
	};
	
	var kakoKeisanZairyoKatazai = {
		kensakuInfo     : 'KakoKeisanZairyoKatazaiInfo',
		tourokuInfo     : 'KakoKeisanZairyoKatazaiInfo',
		ichiran: {
			title				: '材料費（型材）',
			id					: 'KakoKeisanZairyoKatazaiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KakoKeisanZairyoKatazaiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KakoKeisanZairyoKatazaiIchiranGridState'
		},
		toroku: {
			title				: '材料費（型材）登録',
			id					: 'KakoKeisanZairyoKatazaiToroku',
			torokuStorageEnable	: true,
	        torokuStorageName	: 'KakoKeisanZairyoKatazaiTorokuState',
		},
	};
	
	var kakushu = {
			zairyoKbn : {
				kensakuInfo	: 'KakushuKensakuInfo',
				tourokuInfo	: 'KakushuKensakuInfo',	
				ichiran: {
					title				: '材料区分一覧',
					id					: 'ZairyoKbnIchiran',
					searchStorageEnable	: true,
					searchStorageName	: 'ZairyoKbnIchiranKensakuJokenState',
					gridStorageEnable	: true,
					gridStorageName		: 'ZairyoKbnIchiranGridState'
				},
			},
			sagyokoteiKbn : {
				kensakuInfo	: 'KakushuKensakuInfo',
				tourokuInfo	: 'KakushuKensakuInfo',	
				ichiran: {
					title				: '工程一覧',
					id					: 'SagyokoteiIchiran',
					searchStorageEnable	: true,
					searchStorageName	: 'SagyokoteiIchiranKensakuJokenState',
					gridStorageEnable	: true,
					gridStorageName		: 'SagyokoteiIchiranGridState'
				},
			},
			sagyoSetsubiKbn : {
				kensakuInfo	: 'KakushuKensakuInfo',
				tourokuInfo	: 'KakushuKensakuInfo',	
				ichiran: {
					title				: '設備一覧',
					id					: 'SagyoSetsubiIchiran',
					searchStorageEnable	: true,
					searchStorageName	: 'SagyoSetsubiIchiranKensakuJokenState',
					gridStorageEnable	: true,
					gridStorageName		: 'SagyoSetsubiIchiranGridState'
				},
			},
	};
	
	var shohinRenkei = {
			kensakuInfo     : 'ShohinRenkeiKensakuInfo',
			tourokuInfo     : 'ShohinRenkeiInfo',
			toroku: {
				title				: '商品マスタ連携',
				id					: 'ShohinRenkeiToroku',
				torokuStorageEnable	: true,
		        torokuStorageName	: 'ShohinRenkeiTorokuState',
			},
		};
	
	var koteiSetsubi = {
		kensakuInfo     : 'KoteiSetsubiKensakuInfo',
		tourokuInfo     : 'KoteiSetsubiInfo',
		ichiran: {
			title				: '工程設備マスタ照会・保守',
			id					: 'KoteiSetsubiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'KoteiSetsubiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'KoteiSetsubiIchiranGridState'
		},
	};
	
	var buhinSeisakuChokurohi = {
		kensakuInfo     : 'BuhinSeisakuChokurohiKensakuInfo',
		torokuInfo     : 'BuhinSeisakuChokurohiInfo',
		ichiran: {
			title				: '部品製作直労費照会・保守',
			id					: 'BuhinSeisakuChokurohiIchiran',
			searchStorageEnable	: true,
			searchStorageName	: 'BuhinSeisakuChokurohiIchiranKensakuJokenState',
			gridStorageEnable	: true,
			gridStorageName		: 'BuhinSeisakuChokurohiIchiranGridState'
		}
	};
	
	
	var genkasekisan = {
		title:'原価積算',
		
		kensakuInfo	: 'GenkaSekisanIchiranKensakuInfo',
		resultInfo	: 'GenkaSekisanIchiranMeisaiInfo',
				
		genkaInfo	: 'GenkaSekisanInfo',
		torokuInfo	: 'GenkaSekisanInfo',
		meisaiInfo	: 'GenkaSekisanMeisaiInfo',
		kakoInfo	: 'GenkaSekisanKakoInfo',
		attachedInfo	: 'GenkaSekisanAttachedFileInfo',
		mitsumoriRenkeiInfo	: 'MitsumoriGenkaRenkeiInfo',
		fileName	: '原価積算書.xlsx',
		
		ichiran:{
			title:'原価積算一覧',
			id:'GenkaSekisanIchiran',
			
			isSaiKensaku: true,
			gridStorageEnable: true,
			gridStorageName:'GenkaSekisanIchiranKensakuGridState'
		},
		toroku:{
			title:'原価積算登録',
			id:'GenkaSekisanToroku'
		},
		
		state:{
			ichiran:'genkaSekisanIchiran',
			toroku:'genkaSekisanToroku',
			create:'genkaSekisanToroku/create',
			teisei:'genkaSekisanToroku/teisei',
			mitsumori:'genkaSekisanToroku/mitsumori',
			seisakuShiji:'genkaSekisanToroku/seisakuShiji',
		}
	};
	
	var seisakuShiji = {
	    title: '製作指示'
	    ,fileName: '製作指示書'
	    	
	    , kensakuInfo: 'SeisakuShijiIchiranKensakuInfo'
	    , resultInfo: 'SeisakuShijiIchiranMeisaiInfo'
	    	
	    , genkaRenkeiInfo: 'SeisakuShijiGenkaRenkeiInfo'
	    , genkaKousuSumInfo: 'SeisakuShijiGenkaKousuSumInfo'
	    , meisaiInfo: 'SeisakuShijiMeisaiInfo'
	    , setsudanInfo: 'SeisakuShijiSetsudanMeisaiInfo'
	    , koteiInfo: 'SeisakuShijiKoteiInfo'
	    , zairyoHikiateInfo: 'SeisakuShijiZairyoHikiateMeisaiInfo'	
	    	    
	    , ichiran: {
	        title: '製作指示一覧'
	        , id: 'SeisakuShijiIchiran'
	        
	        , isSaiKensaku: true	
	        , gridStorageEnable: true
	        , gridStorageName: 'SeisakuShijiIchiranKensakuGridState'
	    }
	    , toroku: {
	        title: '製作指示登録'
	        , id: 'SeisakuShijiToroku'
		    , gridStorageEnable: true
		    , gridStorageName: 'SeisakuShijiTorokuGridState' 	
	    }
	    , meisaiToroku: {
	        title: '製作指示明細登録'
	        , id: 'SeisakuShijiMeisaiToroku'
	    }
	    ,state: {
			ichiran:'seisakuShijiIchiran',
			toroku:'seisakuShijiToroku',
			
			create:'seisakuShijiToroku/create',
			teisei:'seisakuShijiToroku/teisei',
			teiseiFromMeisai:'seisakuShijiToroku/teiseiFromMeisai',
			
			meisaiCreate:'seisakuShijiMeisaiToroku/create',
			meisaiTeisei:'seisakuShijiMeisaiToroku/teisei',
		}
	};
	
	var shiireRireki = {
			title:'仕入履歴一覧',
			kensakuInfo	: 'BuhinKensakuInfo',
			tourokuInfo	: 'BuhinInfo',
	};
	
	var buhinKakoJoho = {
			title:'部品加工情報検索',
			kensakuInfo : 'BuhinSeisakuChokurohiKensakuInfo',
	};
	
	var genkajoho = {
			title:'原価情報検索',
			kensakuInfo : 'genkasekisanIchiranKensakuInfo',
	}
	
	var seisakuShijiMeisaiIchiran = {
			title: '製作指示明細一覧',
			kensakuInfo: 'SeisakuShijiIchiranKensakuInfo',
	}
	
	var tantoshaHoshu = {
			kensakuInfo    : 'TantoshaHoshuKensakuInfo',
			torokuInfo     : 'TantoshaInfo',
			torokuFrontid  : 'TantoshaMasterMente',
			koshinFrontid  : 'TantoshaMasterMente',
			ichiran: {
				title				: '担当者マスタ照会・保守',
				id					: 'TantoshaHoshuIchiran',
				searchStorageEnable	: true,
				searchStorageName	: 'TantoshaHoshuIchiranKensakuJokenState',
				gridStorageEnable	: true,
				gridStorageName		: 'TantoshaHoshuIchiranGridState'
			},
		};
	//-----------------------------------------------------------------------------------------------------------
	
	//モード
	var mode = {
		shinki : '1',
		teisei : '2',
		kaitei : '3',
		isShinki : function(mode){
			return '1'===mode;
		}
		,isTeisai : function(mode){
			return '2'===mode;
		}		
	};
	
	//行操作
	var gyoSosa = {
		add    : '1',
		copy   : '2',
		remove : '3',
		edit   : '4',
		isAdd : function(type){
			return '1'===type;
		}
		,isCopy : function(type){
			return '2'===type;
		}
		,isRemove : function(type){
			return '3'===type;
		}
		,isEdit : function(type){
			return '4'===type;
		}
		,isNull : function(type){
	        if (type === undefined) {
	            return false;
	        }
	        if (type == null || type === '') {
	            return false;
	        }
	        return true;
		}
	};
	
	// 定数内容
    var value = {
    	mode         : mode,	
    	gyoSosa      : gyoSosa,
    	
    	message      : message,	
    	kengen       : kengen,
    	company      : company,
    	system       : system,
    	alert        : alert,
    	uigrid       : uigrid,
    	flag         : flag,
    	kakuteiFlg   : kakuteiFlg,
    	keishiki     : keishiki,
    	shoninJokyo  : shoninJokyo,
    	seizoKonyuBunrui1 : seizoKonyuBunrui1,
    	buhin        : buhin,
    	tantosha     : tantosha,
    	torihikisaki : torihikisaki,
    	shiresaki    : shiresaki,
    	tokuisaki    : tokuisaki,
    	gaichusaki   : gaichusaki,
    	hinmoku      : hinmoku,
    	yoto         : yoto,
    	hinmokubetsuChokurohi : hinmokubetsuChokurohi,
    	
    	mitsumori         : mitsumori,
    	
    	kakoKeisan : kakoKeisan,
    	kakoKeisanGaichuKakohin : kakoKeisanGaichuKakohin,
    	kakoKeisanHozai : kakoKeisanHozai,
    	kakoKeisanKakohi : kakoKeisanKakohi,
    	kakoKeisanKakotsukiAlumiZai : kakoKeisanKakotsukiAlumiZai,
    	kakoKeisanNaiseiChukanBuhin : kakoKeisanNaiseiChukanBuhin,
    	kakoKeisanZairyoItazai : kakoKeisanZairyoItazai,
    	kakoKeisanZairyoKatazai : kakoKeisanZairyoKatazai,
    	
    	appConst: appConst,
    	kakushuMasterKbn : kakushuMasterKbn,
    	shohinRenkei : shohinRenkei,
    	formatPtn : formatPtn,
    	meisaiGyoPtn : meisaiGyoPtn,
    	mitsumoriJotai : mitsumoriJotai,
    	
    	kakushu:kakushu,
    	koteiSetsubi: koteiSetsubi,
    	
    	buhinSeisakuChokurohi : buhinSeisakuChokurohi,
    	
    	genkasekisan:genkasekisan,
    	
    	seisakuShiji:seisakuShiji,
    	
    	shiireRireki:shiireRireki,
    	
    	buhinKakoJoho:buhinKakoJoho,
    	
    	genkajoho:genkajoho,
    	
    	seisakuShijiMeisaiIchiran:seisakuShijiMeisaiIchiran,
    	
    	tantoshaHoshu : tantoshaHoshu
    };

    angular.module('app').constant('SysConst', value);

}());