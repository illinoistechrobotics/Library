package genericRobot;
//    Copyright (C) 2011  Illinois Institute of Technology Robotics
//	  <robotics@iit.edu>
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License along
//    with this program; if not, write to the Free Software Foundation, Inc.,
//    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

import general.Communication;
import general.EventEnum;
import general.RobotEvent;
import gui.Display;

/**
 * This is where robot specific code goes
 */
public class Events {
	
	public Communication comm = null;
	public int robotConnect = 0;
	public Display dis = null;
	
	public Events(Communication c, Display d){
		this.comm = c;
		this.dis = d;
	}
	
	
	// on_init runs when the program starts up after the joystick and the network
	// has been intitalized
	public void on_init(){

	}
	
	// on_status_code is called when the remote computer sends a status datagram
	public void on_status_code(RobotEvent ev){
		
	}

	// on_command_code is called when the remote computer sends a command datagram
	public void on_command_code(RobotEvent ev){
		switch(ev.getCommand()) {
		  case ROBOT_EVENT_CMD_NOOP:
		    robotConnect = 0;
		    dis.changeRobotStatus(robotConnect);
		    break;
		  case ROBOT_EVENT_CMD_START:
		    break;
		  case ROBOT_EVENT_CMD_STOP:
		    break;
		  case ROBOT_EVENT_CMD_REBOOT:
			  comm.sendEvent(ev);
		    break;
		  default:
		    // unknown command code datagram
		    break;
		  }
	}
	
	// on_axis_change is called when a joystick axis changes postion
	// index is the axis number, value is the position of the axis from 0-255
	// a value of 127 is center
	public void on_axis_change(RobotEvent ev){
		
	}
	
	// on_button_up is called when a joystick button is released
	// button is the button number
	public void on_button_up(RobotEvent ev){
		
		comm.sendEvent(ev);
	}

	// on_button_down is called when a joystick button is pressed
	// button is the button number
	public void on_button_down(RobotEvent ev){
		
	}
	
	// timer that runs each second
	public void on_1hz_timer(RobotEvent ev){
		robotConnect++;
		if(robotConnect > 1){
			dis.changeRobotStatus(1);
		}
	}

	// timer that runs every .1 second (100ms)
	public void on_10hz_timer(RobotEvent ev){
		RobotEvent newEvent = new RobotEvent(EventEnum.ROBOT_EVENT_CMD_NOOP,(short)0,0);
		comm.sendEvent(newEvent);
	}
	
	// on_read_variable is called when the computer wants to read a robo variable
	public void on_read_variable(RobotEvent ev){
		
	}

	// on_set_variable is called when the computer wants to set a robo variable
	public void on_set_variable(RobotEvent ev){
		
	}
	
	// on_shutdown runs when the robot should shutdown and a manual hard reset needed to turn back on. 
	public void on_shutdown(){
		
	}
}
