import axios from "axios";
import {FLATS_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";
import {Button, Form, InputNumber} from "antd";
import {validateIntegerGreaterThanZero} from "./validators";
import {useForm} from "antd/es/form/Form";

export function DeleteFlatForm() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [deleteForm] = useForm();

    const handleFlatDelete = (e) => {
        axios.delete(`${FLATS_API}/${e['id']}`)
            .then((response) => {
                    enqueueSnackbar(`Successfully deleted flat with id ${e['id']}`, {
                        autoHideDuration: 5000,
                        variant: "success"
                    })
                }
            )
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            })
    }

    return (
        <>
            <Form
                form={deleteForm}
                onFinish={handleFlatDelete}
                layout="inline"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
            >
                <Form.Item
                    style={{width: 200}}
                    label="ID"
                    name="id"
                    rules={[
                        {required: true, message: 'Please input ID!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={deleteForm.submit} style={{width: 150}}>
                        Delete flat
                    </Button>
                </Form.Item>
            </Form>
        </>
    )
}