import {Button} from "antd";
import {useState} from "react";
import axios from "axios";
import {CALCULATE_PRICES_SUM} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";;
import {parseHeightSumResponse} from "../../utils/parsers/xml/simple-requests-parser";
import {SimpleResponseModel} from "./general/simple-response-modal";

export function GetTotalCostModal() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [modalVisible, setModalVisible] = useState(false);
    const [modalValue, setModalValue] = useState();

    const handelOpen = () => {
        axios.get(CALCULATE_PRICES_SUM)
            .then((response) => {
                const parsedResponse = parseHeightSumResponse(response.data)
                setModalValue(parsedResponse)
                setModalVisible(true);
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    }

    const handelModalOk = () =>{
        setModalVisible(false)
    }

    return (
        <>
            <Button type="primary" onClick={handelOpen}>
                Calculate total prices sum of all flats
            </Button>
            <SimpleResponseModel
                title="Calculation result"
                visible={modalVisible}
                value={modalValue}
                handleOk={handelModalOk}
            />
        </>
    )
}