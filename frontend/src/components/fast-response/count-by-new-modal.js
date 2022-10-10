import {SimpleResponseModel} from "./general/simple-response-modal";
import {Button, Form, Radio} from "antd";
import {useState} from "react";
import axios from "axios";
import {COUNT_BY_NEW_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";
import {parseCountByNewResponse, parseHeightSumResponse} from "../../utils/parsers/xml/simple-requests-parser";
import {useForm} from "antd/es/form/Form";

export function CountByNewModal() {
    const [countForm] = useForm();
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [modalVisible, setModalVisible] = useState(false);
    const [modalValue, setModalValue] = useState();

    const handelOpen = (e) => {
        axios.get(`${COUNT_BY_NEW_API}?value=${e['new']}`)
            .then((response) => {
                const parsedResponse = parseCountByNewResponse(response.data)
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

    const handelModalOk = () => {
        setModalVisible(false)
    }

    return (
        <>
            <Form
                form={countForm}
                onFinish={handelOpen}
                layout="inline"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
            >
                <Form.Item
                    style={{width: 200}}
                    label="New"
                    name="new"
                    rules={[
                        {required: true, message: 'Please select value!'},
                    ]}
                >
                    <Radio.Group>
                        <Radio value="true">Yes</Radio>
                        <Radio value="false">No</Radio>
                    </Radio.Group>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={countForm.submit} style={{width: 150}}>
                        Calculate by new
                    </Button>
                </Form.Item>
            </Form>
            <SimpleResponseModel
                title="Calculation result"
                visible={modalVisible}
                value={modalValue}
                handleOk={handelModalOk}
            />
        </>
    )
}