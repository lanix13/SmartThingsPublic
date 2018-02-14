/**
 *  Garage Door Toggle
 *
 *  Copyright 2018 Lance Grover
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
preferences {
    

}
 
 
metadata {
	definition (name: "Garage Door Toggle", namespace: "lanix13", author: "Lance Grover") {
		capability "Momentary"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("button", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: 'Off', action: "switch.push", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState: "on"
				state "on", label: 'On', action: "switch.push", icon: "st.switches.switch.on", backgroundColor: "#79b821", nextState: "off"
		}
	//	standardTile("offButton", "device.button", width: 1, height: 1, canChangeIcon: true) {
	//		state "default", label: 'Force Off', action: "switch.push", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
	//	}
	//	standardTile("onButton", "device.switch", width: 1, height: 1, canChangeIcon: true) {
	//		state "default", label: 'Force On', action: "switch.push", icon: "st.switches.switch.on", backgroundColor: "#79b821"
	//	}
		main "button"
			details (["button"])
            //details (["button","onButton","offButton"])            
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"

}

// handle commands
def push() {
	    log.debug "Executing 'push'"
		
		sendEvent(name: "switch", value: "on") 
		            
        def ip = "10.0.0.157:8888"
        def pathorparams = "/garagedoor.php?toggle=yes"
        sendHubCommand(new physicalgraph.device.HubAction("""GET /$pathorparams HTTP/1.1\r\nHOST: $ip\r\n\r\n""", physicalgraph.device.Protocol.LAN, "0a00009d:22B8"))
}